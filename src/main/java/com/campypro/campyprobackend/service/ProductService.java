package com.campypro.campyprobackend.service;

import com.campypro.campyprobackend.entity.Product;
import java.util.List;
import java.util.Optional;

public interface ProductService {
    Product saveProduct(Product product);
    Optional<Product> getProductById(Long id);
    Optional<Product> getProductBySlug(String slug);
    List<Product> getAllProducts();
    void deleteProduct(Long id);
} 