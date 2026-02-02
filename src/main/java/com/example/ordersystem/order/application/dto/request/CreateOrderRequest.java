package com.example.ordersystem.order.application.dto.request;

import com.example.ordersystem.order.application.dto.command.CreateOrderCommand;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record CreateOrderRequest(
        Long customerId,
        Long productId,

        @NotNull
        @Min(value = 1, message = "주문 수량은 최소 1개 이상이어야 합니다.")
        Integer quantity
) {

    public CreateOrderCommand toCommand() {
        return new CreateOrderCommand(
                customerId,
                productId,
                quantity
        );
    }
}
