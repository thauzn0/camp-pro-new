package com.campypro.campyprobackend.service;

import com.campypro.campyprobackend.entity.Cart;
import com.campypro.campyprobackend.entity.User;
import java.util.List;
import java.util.Optional;

public interface CartService {
    Cart saveCart(Cart cart);
    Optional<Cart> getCartById(Long id);
    List<Cart> getAllCarts();
    void deleteCart(Long id);
    Cart getOrCreateActiveCartByUser(User user);
} 