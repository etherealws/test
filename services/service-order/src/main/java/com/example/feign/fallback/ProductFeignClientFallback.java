package com.example.feign.fallback;

import com.example.Product;
import com.example.feign.ProductFeignClient;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class ProductFeignClientFallback implements ProductFeignClient {
    @Override
    public Product getProductById(Long id) {
        System.out.println("兜底回调");
        Product product = new Product();
        product.setId(id);
        product.setProductName("兜底回调");
        product.setPrice(new BigDecimal(0));
        product.setNum(0);
        return product;
    }
}
