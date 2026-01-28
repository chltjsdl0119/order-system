package com.example.ordersystem.product.domain;

import java.math.BigDecimal;

public class Product {
    private final Long id;
    private final String name;
    private final String description;
    private final BigDecimal price;

    private Product(Long id, String name, String description, BigDecimal price) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
    }

    public static Product of(String name, String description, BigDecimal price) {
        validateName(name);
        validatePrice(price);
        return new Product(null, name, description, price);
    }

    public static Product restore(Long id, String name, String description, BigDecimal price) {
        return new Product(id, name, description, price);
    }

    private static void validateName(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("상품 이름은 필수입니다.");
        }
    }

    private static void validatePrice(BigDecimal price) {
        if (price == null || price.signum() < 0) {
            throw new IllegalArgumentException("상품 가격은 0 이상이어야 합니다.");
        }
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public BigDecimal getPrice() {
        return price;
    }
}
