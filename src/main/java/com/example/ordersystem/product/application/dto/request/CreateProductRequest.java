package com.example.ordersystem.product.application.dto.request;

import com.example.ordersystem.product.application.dto.command.CreateProductCommand;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;

public record CreateProductRequest(
        @NotBlank(message = "상품명은 필수입니다.")
        @Size(max = 100, message = "상품명은 100자를 초과할 수 없습니다.")
        String name,

        @Size(max = 1000, message = "상품 설명은 1000자를 초과할 수 없습니다.")
        String description,

        @NotNull(message = "가격은 필수입니다.")
        @DecimalMin(value = "0.01", message = "가격은 0보다 커야 합니다.")
        Double price,

        @NotNull(message = "재고 수량은 필수입니다.")
        @PositiveOrZero(message = "재고 수량은 0 이상이어야 합니다.")
        Integer stock
) {
    public CreateProductCommand toCommand() {
        return new CreateProductCommand(
                name,
                description,
                price,
                stock
        );
    }
}
