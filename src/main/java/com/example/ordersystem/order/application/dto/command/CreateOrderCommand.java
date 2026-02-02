package com.example.ordersystem.order.application.dto.command;

public record CreateOrderCommand(
        Long customerId,
        Long productId,
        Integer quantity
) {
}
