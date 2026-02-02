package com.example.ordersystem.order.application.dto.response;

import com.example.ordersystem.order.domain.Order;

public record OrderResponse(
        Long orderId,
        Long customerId,
        Long productId,
        String customerName,
        String productName,
        Integer quantity,
        Double totalPrice,
        String status
) {

    public static OrderResponse from(Order order) {
        return new OrderResponse(
                order.getId(),
                order.getCustomer().getId(),
                order.getProduct().getId(),
                order.getCustomer().getName(),
                order.getProduct().getName(),
                order.getQuantity(),
                order.getPriceAtOrderTime().doubleValue(),
                order.getStatus().name()
        );
    }
}
