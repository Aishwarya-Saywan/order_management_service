package com.test.order_management.exception;

import com.test.order_management.enums.OrderStatus;

public class InvalidStatusTransitionException extends RuntimeException {
    public InvalidStatusTransitionException(OrderStatus current, OrderStatus next) {
        super("Invalid status transition from " + current + " to " + next);
    }
}