package com.example.ordersystem.order.infrastructure;

import com.example.ordersystem.order.domain.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface OrderJpaRepository extends JpaRepository<Order, Long> {

    @Query("""
                SELECT o FROM Order o
                JOIN FETCH o.customer
                JOIN FETCH o.product
                WHERE o.id = :id
            """)
    Optional<Order> findByIdWithCustomerAndProduct(Long id);

    @Query("""
                SELECT o FROM Order o
                JOIN FETCH o.customer
                JOIN FETCH o.product
                WHERE o.customer.id = :customerId
            """)
    Page<Order> findOrdersWithProductByCustomerId(Long customerId, Pageable pageable);
}
