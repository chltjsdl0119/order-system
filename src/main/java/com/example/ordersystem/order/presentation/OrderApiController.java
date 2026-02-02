package com.example.ordersystem.order.presentation;

import com.example.ordersystem.order.application.dto.request.CreateOrderRequest;
import com.example.ordersystem.order.application.dto.response.OrderResponse;
import com.example.ordersystem.order.domain.service.OrderService;
import com.example.ordersystem.order.presentation.docs.OrderApiDocs;
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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderApiController implements OrderApiDocs {
    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<Long> createOrder(@Valid @RequestBody CreateOrderRequest request) {
        Long orderId = orderService.createOrder(request.toCommand());
        return ResponseEntity.ok(orderId);
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<OrderResponse> getOrderDetails(@PathVariable Long orderId) {
        OrderResponse orderResponse = orderService.getOrderDetails(orderId);
        return ResponseEntity.ok(orderResponse);
    }

    @GetMapping("/customer/{customerId}")
    public ResponseEntity<Page<OrderResponse>> getOrdersByCustomerId(@PathVariable Long customerId,
                                                                     @PageableDefault(size = 20, sort = "id", direction = Sort.Direction.DESC)
                                                                     Pageable pageable) {
        Page<OrderResponse> responses = orderService.getOrdersByCustomerId(customerId, pageable);
        return ResponseEntity.ok(responses);
    }
}
