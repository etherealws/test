package com.example.service.impl;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.example.Order;
import com.example.Product;
import com.example.feign.ProductFeignClient;
import com.example.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

@Slf4j
@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private DiscoveryClient discoveryClient;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private ProductFeignClient productFeignClient;

    @Autowired
    private LoadBalancerClient loadBalancerClient;

    @SentinelResource(value = "createOrder",blockHandler = "createOrderFallback")
    @Override
    public Order createOrder(Long productId, Long userId) {
//        Product product = getProductFromRemote(productId);
        Product product = productFeignClient.getProductById(productId);
        Order order = new Order();
        order.setId(1L);
        order.setTotalAmount(new BigDecimal(String.valueOf(product.getPrice().multiply(new BigDecimal(product.getNum())))));
        order.setUserId(userId);
        order.setNickName("张三");
        order.setAddress("测试");
        order.setProductList(Arrays.asList(product));
        return order;
    }

    //兜底回调
    public Order createOrderFallback(Long productId, Long userId, BlockException e) {
        Order order = new Order();
        order.setId(0L);
        order.setTotalAmount(new BigDecimal(0));
        order.setUserId(userId);
        order.setNickName("未知用户");
        order.setAddress("异常信息"+e.getClass());
        return order;
    }

    private Product getProductFromRemote(Long productId){
        //获取
        List<ServiceInstance> instances = discoveryClient.getInstances("service-product");
        ServiceInstance serviceInstance = instances.get(0);
        String url = "http://"+serviceInstance.getHost() + ":" + serviceInstance.getPort()+"/product/"+productId;
        log.info("远程请求路径"+url);
        //调用
        Product product = restTemplate.getForObject(url, Product.class);
        return product;
    }

    //带负载均衡
    private Product getProductFromRemoteWithLoadBalance(Long productId){
        //获取
        ServiceInstance choose = loadBalancerClient.choose("service-product");
        String url = "http://"+choose.getHost() + ":" + choose.getPort()+"/product/"+productId;
        log.info("远程请求路径"+url);
        //调用
        Product product = restTemplate.getForObject(url, Product.class);
        return product;
    }

    //注解负载均衡
    private Product getProductFromRemoteWithLoadBalanceAnnotation(Long productId){
        String url = "http://service-product/product/"+productId;
        log.info("远程请求路径"+url);
        //调用
        Product product = restTemplate.getForObject(url, Product.class);
        return product;
    }
}
