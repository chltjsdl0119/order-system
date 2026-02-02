package com.example.ordersystem.product.presentation.docs;


import com.example.ordersystem.product.application.dto.request.CreateProductRequest;
import com.example.ordersystem.product.application.dto.request.UpdateProductRequest;
import com.example.ordersystem.product.application.dto.response.ProductResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

@Tag(name = "Product API", description = "상품 관련 API 문서")
public interface ProductApiDocs {

    @Operation(
            summary = "상품 생성",
            description = "상품을 신규로 등록한다."
    )
    @ApiResponse(
            responseCode = "200",
            description = "상품 생성 성공",
            content = @Content(schema = @Schema(implementation = Long.class))
    )
    ResponseEntity<Long> createProduct(
            @Parameter(description = "상품 생성 요청 DTO", required = true)
            CreateProductRequest request
    );

    @Operation(
            summary = "상품 수정",
            description = "기존 상품 정보를 수정한다."
    )
    @ApiResponse(
            responseCode = "204",
            description = "상품 수정 성공"
    )
    ResponseEntity<Void> updateProduct(
            @Parameter(description = "상품 ID", example = "1")
            Long productId,
            UpdateProductRequest request
    );

    @Operation(
            summary = "상품 단건 조회",
            description = "상품 ID로 상품을 조회한다."
    )
    @ApiResponse(
            responseCode = "200",
            description = "조회 성공",
            content = @Content(schema = @Schema(implementation = ProductResponse.class))
    )
    ResponseEntity<ProductResponse> getProduct(
            @Parameter(description = "상품 ID", example = "1")
            Long productId
    );

    @Operation(
            summary = "상품 목록 조회",
            description = "페이징 기반 상품 목록을 조회한다."
    )
    @ApiResponse(
            responseCode = "200",
            description = "조회 성공"
    )
    ResponseEntity<Page<ProductResponse>> getAllProducts(
            @Parameter(hidden = true)
            Pageable pageable
    );
}
