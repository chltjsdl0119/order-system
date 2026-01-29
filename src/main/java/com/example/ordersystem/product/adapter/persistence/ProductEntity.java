package com.example.ordersystem.product.adapter.persistence;

import com.example.ordersystem.product.domain.Product;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.math.BigDecimal;

@Entity
@Table(name = "products")
public class ProductEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;
    private BigDecimal price;

    protected ProductEntity() {
    }

    public ProductEntity(Long id, String name, String description, BigDecimal price) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
    }

    public static ProductEntity from(Product product) {
        return new ProductEntity(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getPrice()
        );
    }

    public Product toDomain() {
        return Product.restore(
                this.id,
                this.name,
                this.description,
                this.price
        );
    }
}
