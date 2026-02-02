package com.example.ordersystem.product.application.dto.request;

import com.example.ordersystem.product.application.dto.command.UpdateProductCommand;

public record UpdateProductRequest(
        String name,
        String description,
        Double price,
        Integer stock
) {
    public UpdateProductCommand toCommand() {
        return new UpdateProductCommand(
                name,
                description,
                price,
                stock
        );
    }
}
