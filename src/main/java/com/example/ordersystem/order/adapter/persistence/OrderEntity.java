package com.example.ordersystem.order.adapter.persistence;

import com.example.ordersystem.order.domain.Order;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.math.BigDecimal;

@Entity
@Table(name = "orders")
public class OrderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long productId;
    private BigDecimal orderPrice;
    private int quantity;

    protected OrderEntity() {
    }

    private OrderEntity(Long id, Long productId, BigDecimal orderPrice, int quantity) {
        this.id = id;
        this.productId = productId;
        this.orderPrice = orderPrice;
        this.quantity = quantity;
    }

    public static OrderEntity from(Order order) {
        return new OrderEntity(
                order.getId(),
                order.getProductId(),
                order.getOrderPrice(),
                order.getQuantity()
        );
    }

    public Order toDomain() {
        return Order.restore(
                this.id,
                this.productId,
                this.orderPrice,
                this.quantity
        );
    }
}
