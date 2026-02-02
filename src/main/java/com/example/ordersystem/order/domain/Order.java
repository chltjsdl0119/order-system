package com.example.ordersystem.order.domain;

import com.example.ordersystem.product.domain.Product;
import com.example.ordersystem.user.domain.User;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Table(name = "orders")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@Getter
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id", nullable = false)
    private User customer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @Column(nullable = false)
    private Integer quantity;

    @Column(nullable = false)
    private BigDecimal priceAtOrderTime;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private OrderStatus status;

    public static Order create(User customer, Product product, int quantity) {
        if (quantity <= 0) throw new IllegalArgumentException("수량은 1 이상이어야 합니다.");

        return Order.builder()
                .customer(customer)
                .product(product)
                .quantity(quantity)
                .priceAtOrderTime(product.getPrice().multiply(BigDecimal.valueOf(quantity)))
                .status(OrderStatus.CREATED)
                .build();
    }

    public void pay() {
        if (status != OrderStatus.CREATED)
            throw new IllegalStateException("결제할 수 없는 상태입니다.");

        status = OrderStatus.PAID;
    }

    public void ship() {
        if (status != OrderStatus.PAID)
            throw new IllegalStateException("배송할 수 없는 상태입니다.");

        status = OrderStatus.SHIPPED;
    }
}
