package com.campypro.campyprobackend.repository;

import com.campypro.campyprobackend.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
} 