package com.example.ordersystem.order.domain;

import java.math.BigDecimal;

public class Order {
    private final Long id;
    private final Long productId;
    private final BigDecimal orderPrice;
    private final int quantity;

    private Order(Long id, Long productId, BigDecimal orderPrice, int quantity) {
        this.id = id;
        this.productId = productId;
        this.orderPrice = orderPrice;
        this.quantity = quantity;
    }

    public static Order of(Long productId, BigDecimal orderPrice, int quantity) {
        validateProductId(productId);
        validateOrderPrice(orderPrice);
        validateQuantity(quantity);
        return new Order(null, productId, orderPrice, quantity);
    }

    public static Order restore(Long id, Long productId, BigDecimal price, int quantity) {
        return new Order(id, productId, price, quantity);
    }

    public BigDecimal totalPrice() {
        return orderPrice.multiply(BigDecimal.valueOf(quantity));
    }

    private static void validateProductId(Long productId) {
        if (productId == null) {
            throw new IllegalArgumentException("상품 ID는 필수입니다.");
        }
    }

    private static void validateOrderPrice(BigDecimal orderPrice) {
        if (orderPrice == null || orderPrice.signum() < 0) {
            throw new IllegalArgumentException("주문 가격은 0 이상이어야 합니다.");
        }
    }

    private static void validateQuantity(int quantity) {
        if (quantity <= 0) {
            throw new IllegalArgumentException("수량은 1개 이상이어야 합니다.");
        }
    }

    public Long getId() {
        return id;
    }

    public Long getProductId() {
        return productId;
    }

    public BigDecimal getOrderPrice() {
        return orderPrice;
    }

    public int getQuantity() {
        return quantity;
    }
}
