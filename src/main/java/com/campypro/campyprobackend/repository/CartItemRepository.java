package com.campypro.campyprobackend.repository;

import com.campypro.campyprobackend.entity.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
} 