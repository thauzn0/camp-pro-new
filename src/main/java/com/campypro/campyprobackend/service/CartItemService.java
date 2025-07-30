package com.campypro.campyprobackend.service;

import com.campypro.campyprobackend.entity.CartItem;
import java.util.List;
import java.util.Optional;

public interface CartItemService {
    CartItem saveCartItem(CartItem cartItem);
    Optional<CartItem> getCartItemById(Long id);
    List<CartItem> getAllCartItems();
    void deleteCartItem(Long id);
} 