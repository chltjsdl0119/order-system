package com.example.ordersystem.order.presentation.docs;

import com.example.ordersystem.order.application.dto.request.CreateOrderRequest;
import com.example.ordersystem.order.application.dto.response.OrderResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

@Tag(name = "Order API", description = "주문 관련 API")
public interface OrderApiDocs {

    @Operation(
            summary = "주문 생성",
            description = "회원이 상품을 주문합니다.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "주문 생성 성공",
                            content = @Content(schema = @Schema(implementation = Long.class))
                    ),
                    @ApiResponse(responseCode = "400", description = "잘못된 요청", content = @Content)
            }
    )
    ResponseEntity<Long> createOrder(
            @RequestBody CreateOrderRequest request
    );

    @Operation(
            summary = "주문 상세 조회",
            description = "주문 ID로 주문 상세 정보를 조회합니다.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "조회 성공",
                            content = @Content(schema = @Schema(implementation = OrderResponse.class))
                    ),
                    @ApiResponse(responseCode = "404", description = "주문을 찾을 수 없음", content = @Content)
            }
    )
    ResponseEntity<OrderResponse> getOrderDetails(
            @Parameter(description = "주문 ID", example = "1")
            @PathVariable Long orderId
    );

    @Operation(
            summary = "회원별 주문 목록 조회",
            description = "특정 회원의 주문 목록을 페이징 조회합니다.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "조회 성공",
                            content = @Content(schema = @Schema(implementation = OrderResponse.class))
                    )
            }
    )
    ResponseEntity<Page<OrderResponse>> getOrdersByCustomerId(
            @Parameter(description = "회원 ID", example = "1")
            @PathVariable Long customerId,

            @Parameter(hidden = true)
            Pageable pageable
    );
}
