package com.campypro.campyprobackend.repository;

import com.campypro.campyprobackend.entity.Cart;
import com.campypro.campyprobackend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CartRepository extends JpaRepository<Cart, Long> {
    Optional<Cart> findByUserAndActiveTrue(User user);
} 