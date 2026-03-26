package com.test.order_management.dto.response;

import com.test.order_management.entity.Order;
import com.test.order_management.enums.OrderStatus;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class OrderResponse {
    private String orderId;
    private String customerName;
    private Double amount;
    private OrderStatus status;

    public static OrderResponse fromEntity(Order order) {
        return new OrderResponse(
                order.getOrderId(),
                order.getCustomerName(),
                order.getAmount(),
                order.getStatus()
        );
    }

    public OrderResponse(String orderId, String customerName, Double amount, OrderStatus status) {
        this.orderId = orderId;
        this.customerName = customerName;
        this.amount = amount;
        this.status = status;
    }
}
