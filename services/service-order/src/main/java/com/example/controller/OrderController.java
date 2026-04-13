package com.example.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.example.Order;
import com.example.properties.OrderProperties;
import com.example.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

//@RefreshScope
@RestController
public class OrderController {
    @Autowired
    private OrderService orderService;
    @Autowired
    private OrderProperties orderProperties;
    // 创建订单
    @GetMapping("/create")
    @SentinelResource(value = "create-Order", blockHandler = "createOrderFallback")
    public Order createOrder(@RequestParam("userId") Long userId,
                             @RequestParam("productId") Long productId) {
        return orderService.createOrder(productId, userId);
    }

    public Order createOrderFallback(Long userId, Long productId, BlockException e) {
        Order order = new Order();
        order.setId(productId);
        order.setUserId(userId);
        order.setAddress("fallback"+e.getClass());
        return order;
    }

    @GetMapping("config")
    public String getConfig() {
        return orderProperties.getTimeout() + "-------" + orderProperties.getAutoConfirm();
    }
}
