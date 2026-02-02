package com.example.ordersystem.product.application.dto.command;

public record UpdateProductCommand(
        String name,
        String description,
        Double price,
        Integer stock
) {
}
