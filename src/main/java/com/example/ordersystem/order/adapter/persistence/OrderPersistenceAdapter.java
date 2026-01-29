package com.example.ordersystem.order.adapter.persistence;

import com.example.ordersystem.order.domain.Order;
import com.example.ordersystem.order.domain.port.OrderRepositoryPort;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class OrderPersistenceAdapter implements OrderRepositoryPort {
    private final OrderJpaRepository orderJpaRepository;

    public OrderPersistenceAdapter(OrderJpaRepository orderJpaRepository) {
        this.orderJpaRepository = orderJpaRepository;
    }

    @Override
    public Order save(Order order) {
        OrderEntity orderEntity = OrderEntity.from(order);
        OrderEntity savedEntity = orderJpaRepository.save(orderEntity);
        return savedEntity.toDomain();
    }

    @Override
    public Optional<Order> findById(Long orderId) {
        return orderJpaRepository.findById(orderId)
                .map(OrderEntity::toDomain);
    }

    @Override
    public List<Order> findAll() {
        return orderJpaRepository.findAll().stream()
                .map(OrderEntity::toDomain)
                .toList();
    }
}
