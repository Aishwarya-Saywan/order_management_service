package com.test.order_management.enums;

import java.util.EnumSet;
import java.util.Set;

public enum OrderStatus {
    NEW,
    PROCESSING,
    COMPLETED;

    private Set<OrderStatus> allowedNextStatuses;

    static {
        NEW.allowedNextStatuses = EnumSet.of(PROCESSING);
        PROCESSING.allowedNextStatuses = EnumSet.of(COMPLETED);
        COMPLETED.allowedNextStatuses = EnumSet.noneOf(OrderStatus.class);
    }

    public boolean canTransitionTo(OrderStatus nextStatus) {
        return allowedNextStatuses.contains(nextStatus);
    }
}
