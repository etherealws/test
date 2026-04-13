package com.example.controller;

import com.example.Product;
import com.example.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController {
    @Autowired
    private ProductService productService;
    //查询商品
    @GetMapping("/product/{id}")
    public Product getPorduct(@PathVariable("id") Long productId){
        return productService.getProductById(productId);
    }
}
