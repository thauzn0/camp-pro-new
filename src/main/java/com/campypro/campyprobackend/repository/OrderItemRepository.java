package com.campypro.campyprobackend.repository;

import com.campypro.campyprobackend.entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
} 