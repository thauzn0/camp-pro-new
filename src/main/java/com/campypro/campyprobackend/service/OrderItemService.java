package com.campypro.campyprobackend.service;

import com.campypro.campyprobackend.entity.OrderItem;
import java.util.List;
import java.util.Optional;

public interface OrderItemService {
    OrderItem saveOrderItem(OrderItem orderItem);
    Optional<OrderItem> getOrderItemById(Long id);
    List<OrderItem> getAllOrderItems();
    void deleteOrderItem(Long id);
} 