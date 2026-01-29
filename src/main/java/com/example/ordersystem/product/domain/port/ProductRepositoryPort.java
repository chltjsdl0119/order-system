package com.example.ordersystem.product.domain.port;

import com.example.ordersystem.product.domain.Product;

import java.util.List;
import java.util.Optional;

public interface ProductRepositoryPort {

    Product save(Product product);

    Optional<Product> findById(Long productId);
    
    List<Product> findAll();
}
