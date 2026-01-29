package com.example.ordersystem.order.domain.port;

import com.example.ordersystem.order.domain.Order;

import java.util.List;
import java.util.Optional;

public interface OrderRepositoryPort {

    Order save(Order order);

    Optional<Order> findById(Long orderId);

    List<Order> findAll();
}
