package com.test.order_management.service.impl;

import com.test.order_management.dto.request.OrderRequest;
import com.test.order_management.dto.response.OrderResponse;
import com.test.order_management.entity.Order;
import com.test.order_management.enums.OrderStatus;
import com.test.order_management.exception.InvalidStatusTransitionException;
import com.test.order_management.exception.OrderNotFoundException;
import com.test.order_management.service.OrderService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

    private final Map<String, Order> orders = new ConcurrentHashMap<>();

    @Override
    public OrderResponse createOrder(OrderRequest orderRequest) {

        String orderId = UUID.randomUUID().toString();
        Order order = Order.builder()
                .orderId(orderId)
                .amount(orderRequest.getAmount())
                .customerName(orderRequest.getCustomerName())
                .status(OrderStatus.NEW)
                .build();

        orders.put(orderId, order);
        return OrderResponse.fromEntity(order);
    }


    @Override
    public OrderResponse getOrder(String orderId) {
        Order order = Optional.ofNullable(orders.get(orderId))
                .orElseThrow(() -> new OrderNotFoundException(orderId));
        return OrderResponse.fromEntity(order);
    }

    @Override
    public OrderResponse updateOrderStatus(String orderId, OrderStatus newStatus) {
        Order order = Optional.ofNullable(orders.get(orderId))
                .orElseThrow(() -> new OrderNotFoundException(orderId));

        if (order.getStatus().canTransitionTo(newStatus)) {
            order.setStatus(newStatus);
            return OrderResponse.fromEntity(order);
        }

        throw new InvalidStatusTransitionException(order.getStatus(), newStatus);
    }

    @Override
    public List<OrderResponse> getAllOrders() {
        return orders.values()
                .stream()
                .map(OrderResponse::fromEntity)
                .collect(Collectors.toList());
    }
}
