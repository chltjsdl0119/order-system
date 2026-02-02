package com.example.ordersystem.product.application.dto.response;

import com.example.ordersystem.product.domain.Product;

public record ProductResponse(
        Long id,
        String name,
        String description,
        Double price,
        Integer stock,
        String status
) {
    public static ProductResponse fromEntity(Product product) {
        return new ProductResponse(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getPrice().doubleValue(),
                product.getStock(),
                product.getStatus().name()
        );
    }
}
