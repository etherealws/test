package com.example.service;

import com.example.Order;

public interface OrderService {
    Order createOrder(Long productId, Long userId);
}
