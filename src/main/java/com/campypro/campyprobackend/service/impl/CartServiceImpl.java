package com.campypro.campyprobackend.service.impl;

import com.campypro.campyprobackend.entity.Cart;
import com.campypro.campyprobackend.entity.User;
import com.campypro.campyprobackend.repository.CartRepository;
import com.campypro.campyprobackend.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CartServiceImpl implements CartService {
    private final CartRepository cartRepository;

    @Autowired
    public CartServiceImpl(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    @Override
    public Cart saveCart(Cart cart) {
        return cartRepository.save(cart);
    }

    @Override
    public Optional<Cart> getCartById(Long id) {
        return cartRepository.findById(id);
    }

    @Override
    public List<Cart> getAllCarts() {
        return cartRepository.findAll();
    }

    @Override
    public void deleteCart(Long id) {
        cartRepository.deleteById(id);
    }

    @Override
    public Cart getOrCreateActiveCartByUser(User user) {
        // Varsayım: Cart entity'de 'user' ve 'active' (veya benzeri) alanı var
        Optional<Cart> activeCartOpt = cartRepository.findByUserAndActiveTrue(user);
        if (activeCartOpt.isPresent()) {
            return activeCartOpt.get();
        } else {
            Cart newCart = new Cart();
            newCart.setUser(user); // <-- Burası önemli!
            newCart.setActive(true);
            return cartRepository.save(newCart);
        }
    }
} 