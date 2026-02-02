package com.example.ordersystem.order.infrastructure;

import com.example.ordersystem.order.domain.Order;
import com.example.ordersystem.order.domain.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class OrderRepositoryImpl implements OrderRepository {
    private final OrderJpaRepository orderJpaRepository;

    @Override
    public Order save(Order order) {
        return orderJpaRepository.save(order);
    }

    @Override
    public Optional<Order> findById(Long id) {
        return orderJpaRepository.findById(id);
    }

    @Override
    public Page<Order> findOrdersWithProductByCustomerId(Long customerId, Pageable pageable) {
        return orderJpaRepository.findOrdersWithProductByCustomerId(customerId, pageable);
    }
}
