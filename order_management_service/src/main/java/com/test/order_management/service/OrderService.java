package com.test.order_management.service;

import com.test.order_management.dto.request.OrderRequest;
import com.test.order_management.dto.response.OrderResponse;
import com.test.order_management.enums.OrderStatus;

import java.util.List;

public interface OrderService {

    OrderResponse createOrder(OrderRequest orderRequest);

    OrderResponse getOrder(String orderId);

    OrderResponse updateOrderStatus(String orderId, OrderStatus newStatus);

    List<OrderResponse> getAllOrders();
}
