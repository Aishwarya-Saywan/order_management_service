package com.test.order_management.entity;

import com.test.order_management.enums.OrderStatus;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class Order {
    private String orderId;
    private String customerName;
    private Double amount;
    private OrderStatus status;
}
