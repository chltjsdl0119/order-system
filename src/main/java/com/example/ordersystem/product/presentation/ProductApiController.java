package com.example.ordersystem.product.presentation;

import com.example.ordersystem.product.application.dto.request.CreateProductRequest;
import com.example.ordersystem.product.application.dto.request.UpdateProductRequest;
import com.example.ordersystem.product.application.dto.response.ProductResponse;
import com.example.ordersystem.product.domain.service.ProductService;
import com.example.ordersystem.product.presentation.docs.ProductApiDocs;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductApiController implements ProductApiDocs {
    private final ProductService productService;

    @Override
    @PostMapping
    public ResponseEntity<Long> createProduct(@Valid @RequestBody CreateProductRequest request) {
        Long productId = productService.createProduct(request.toCommand());
        return ResponseEntity.ok(productId);
    }

    @Override
    @PutMapping("/{productId}")
    public ResponseEntity<Void> updateProduct(@PathVariable Long productId,
                                              @Valid @RequestBody UpdateProductRequest request) {
        productService.updateProduct(productId, request.toCommand());
        return ResponseEntity.noContent().build();
    }

    @Override
    @GetMapping("/{productId}")
    public ResponseEntity<ProductResponse> getProduct(@PathVariable Long productId) {
        ProductResponse response = productService.getProduct(productId);
        return ResponseEntity.ok(response);
    }

    @Override
    @GetMapping
    public ResponseEntity<Page<ProductResponse>> getAllProducts(
            @PageableDefault(size = 20, sort = "id", direction = Sort.Direction.DESC)
            Pageable pageable
    ) {
        Page<ProductResponse> responses = productService.getAllProducts(pageable);
        return ResponseEntity.ok(responses);
    }
}
