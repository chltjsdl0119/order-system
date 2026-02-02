package com.example.ordersystem.product.application.dto.command;

import com.example.ordersystem.product.domain.Product;

import java.math.BigDecimal;

public record CreateProductCommand(
    String name,
    String description,
    Double price,
    Integer stock
) {
    public Product toEntity() {
        return Product.builder()
            .name(name)
            .description(description)
            .price(BigDecimal.valueOf(price))
            .stock(stock)
            .build();
    }
}
