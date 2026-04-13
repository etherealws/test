package com.example.service.impl;

import com.example.Product;
import com.example.service.ProductService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class ProductServiceImpl implements ProductService {
    @Override
    public Product getProductById(Long productId) {
        Product product = new Product();
        product.setId(productId);
        product.setPrice(new BigDecimal("99"));
        product.setProductName("iPhone-"+productId);
        product.setNum(2);
        return product;
    }
}
