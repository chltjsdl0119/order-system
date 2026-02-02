package com.example.ordersystem.order.domain.service;

import com.example.ordersystem.order.application.dto.command.CreateOrderCommand;
import com.example.ordersystem.order.application.dto.response.OrderResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface OrderService {

    Long createOrder(CreateOrderCommand command);

    OrderResponse getOrderDetails(Long orderId);

    Page<OrderResponse> getOrdersByCustomerId(Long customerId, Pageable pageable);
}
