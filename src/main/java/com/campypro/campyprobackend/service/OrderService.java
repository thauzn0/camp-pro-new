package com.campypro.campyprobackend.service;

import com.campypro.campyprobackend.entity.Order;
import java.util.List;
import java.util.Optional;

public interface OrderService {
    Order saveOrder(Order order);
    Optional<Order> getOrderById(Long id);
    List<Order> getAllOrders();
    void deleteOrder(Long id);
} 