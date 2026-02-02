package com.example.ordersystem.product.application;

import com.example.ordersystem.product.application.dto.command.CreateProductCommand;
import com.example.ordersystem.product.application.dto.command.UpdateProductCommand;
import com.example.ordersystem.product.application.dto.response.ProductResponse;
import com.example.ordersystem.product.domain.Product;
import com.example.ordersystem.product.domain.repository.ProductRepository;
import com.example.ordersystem.product.domain.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    @Override
    @Transactional
    public Long createProduct(CreateProductCommand command) {
        Product product = command.toEntity();
        return productRepository.save(product).getId();
    }

    @Override
    @Transactional
    public void updateProduct(Long productId, UpdateProductCommand command) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new IllegalArgumentException("상품을 찾을 수 없습니다. id: " + productId));

        product.updateDetails(command.name(), command.description(), command.price(), command.stock());
    }

    @Override
    public ProductResponse getProduct(Long productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new IllegalArgumentException("상품을 찾을 수 없습니다. id: " + productId));

        return ProductResponse.fromEntity(product);
    }

    @Override
    public Page<ProductResponse> getAllProducts(Pageable pageable) {
        return productRepository.findAll(pageable)
                .map(ProductResponse::fromEntity);
    }

    @Transactional
    public void deleteProduct(Long productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new IllegalArgumentException("상품 없음"));

        product.deactivate();
    }
}
