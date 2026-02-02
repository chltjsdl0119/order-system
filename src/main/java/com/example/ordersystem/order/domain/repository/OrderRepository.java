package com.example.ordersystem.order.domain.repository;

import com.example.ordersystem.order.domain.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface OrderRepository {

    Order save(Order order);

    Optional<Order> findByIdWithCustomerAndProduct(Long id);

    Page<Order> findOrdersWithProductByCustomerId(Long customerId, Pageable pageable);
}
