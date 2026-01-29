package com.example.ordersystem.product.adapter.persistence;

import com.example.ordersystem.product.domain.Product;
import com.example.ordersystem.product.domain.port.ProductRepositoryPort;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ProductPersistenceAdapter implements ProductRepositoryPort {
    private final ProductJpaRepository productJpaRepository;

    public ProductPersistenceAdapter(ProductJpaRepository productJpaRepository) {
        this.productJpaRepository = productJpaRepository;
    }

    @Override
    public Product save(Product product) {
        ProductEntity productEntity = ProductEntity.from(product);
        ProductEntity savedEntity = productJpaRepository.save(productEntity);
        return savedEntity.toDomain();
    }

    @Override
    public Optional<Product> findById(Long productId) {
        return productJpaRepository.findById(productId)
                .map(ProductEntity::toDomain);
    }

    @Override
    public List<Product> findAll() {
        List<ProductEntity> entities = productJpaRepository.findAll();
        return entities.stream()
                .map(ProductEntity::toDomain)
                .toList();
    }
}
