package com.test.order_management;

import com.test.order_management.dto.request.OrderRequest;
import com.test.order_management.dto.response.OrderResponse;
import com.test.order_management.exception.OrderNotFoundException;
import com.test.order_management.service.impl.OrderServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class OrderServiceImplTest {

    private OrderServiceImpl orderService;

    @BeforeEach
    void setUp() {
        orderService = new OrderServiceImpl();
    }

    @Test
    void testCreateOrder() {

        OrderRequest request = new OrderRequest();
        request.setCustomerName("Aishwarya");
        request.setAmount(5000.0);

        OrderResponse response = orderService.createOrder(request);

        Assertions.assertNotNull(response);
        Assertions.assertNotNull(response.getOrderId());
        Assertions.assertEquals("Aishwarya", response.getCustomerName());
        Assertions.assertEquals(5000.0, response.getAmount());
        Assertions.assertEquals("NEW", response.getStatus().name());
    }

    @Test
    void testGetOrder() {

        OrderRequest request = new OrderRequest();
        request.setCustomerName("Rahul");
        request.setAmount(3000.0);

        OrderResponse created = orderService.createOrder(request);

        OrderResponse fetched = orderService.getOrder(created.getOrderId());

        Assertions.assertEquals(created.getOrderId(), fetched.getOrderId());
        Assertions.assertEquals("Rahul", fetched.getCustomerName());
        Assertions.assertEquals(3000.0, fetched.getAmount());
    }

    @Test
    void testGetOrder_NotFound() {

        Assertions.assertThrows(OrderNotFoundException.class, () -> {
            orderService.getOrder("invalid-id");
        });
    }
}