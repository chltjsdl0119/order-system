package com.example.ordersystem.product.domain.service;

import com.example.ordersystem.product.application.dto.command.CreateProductCommand;
import com.example.ordersystem.product.application.dto.command.UpdateProductCommand;
import com.example.ordersystem.product.application.dto.response.ProductResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductService {

    Long createProduct(CreateProductCommand command);

    void updateProduct(Long productId, UpdateProductCommand command);

    ProductResponse getProduct(Long productId);

    Page<ProductResponse> getAllProducts(Pageable pageable);
}
