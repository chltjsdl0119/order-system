package com.example.ordersystem.order.application;

import com.example.ordersystem.order.application.dto.command.CreateOrderCommand;
import com.example.ordersystem.order.application.dto.response.OrderResponse;
import com.example.ordersystem.order.domain.Order;
import com.example.ordersystem.order.domain.repository.OrderRepository;
import com.example.ordersystem.order.domain.service.OrderService;
import com.example.ordersystem.product.domain.Product;
import com.example.ordersystem.product.domain.repository.ProductRepository;
import com.example.ordersystem.user.domain.User;
import com.example.ordersystem.user.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    @Override
    @Transactional
    public Long createOrder(CreateOrderCommand command) {
        User user = userRepository.findById(command.customerId())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 회원입니다. id: " + command.customerId()));

        Product product = productRepository.findById(command.productId())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 상품입니다. id: " + command.productId()));

        product.decreaseStock(command.quantity());

        Order order = Order.create(user, product, command.quantity());

        return orderRepository.save(order).getId();
    }

    @Override
    public OrderResponse getOrderDetails(Long orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 주문입니다. id: " + orderId));

        return OrderResponse.from(order);
    }

    @Override
    public Page<OrderResponse> getOrdersByCustomerId(Long customerId, Pageable pageable) {
        Page<Order> orders = orderRepository.findOrdersWithProductByCustomerId(customerId, pageable);
        return orders.map(OrderResponse::from);
    }
}
