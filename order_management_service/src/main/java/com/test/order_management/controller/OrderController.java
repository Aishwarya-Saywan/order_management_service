package com.test.order_management.controller;

import com.test.order_management.dto.request.OrderRequest;
import com.test.order_management.dto.response.ApiResponse;
import com.test.order_management.dto.response.OrderResponse;
import com.test.order_management.enums.OrderStatus;
import com.test.order_management.service.OrderService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }


    @PostMapping
    public ResponseEntity<ApiResponse<OrderResponse>> createOrder(@Valid @RequestBody OrderRequest request) {
        OrderResponse order = orderService.createOrder(request);
        ApiResponse<OrderResponse> response = new ApiResponse<>(true, "Order created successfully", order);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<ApiResponse<OrderResponse>> getOrder(@PathVariable String orderId) {
        OrderResponse order = orderService.getOrder(orderId);
        return ResponseEntity.ok(new ApiResponse<>(true, "Order fetched successfully", order));
    }

    @PutMapping("/{orderId}/status")
    public ResponseEntity<ApiResponse<OrderResponse>> updateOrderStatus(@PathVariable String orderId,
                                                                 @RequestParam OrderStatus orderStatus) {
        OrderResponse updatedOrder = orderService.updateOrderStatus(orderId,orderStatus);
        return ResponseEntity.ok(new ApiResponse<>(true, "Order status updated successfully", updatedOrder));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<OrderResponse>>> getAllOrders() {
        List<OrderResponse> orders = orderService.getAllOrders();
        return ResponseEntity.ok(new ApiResponse<>(true, "All orders fetched successfully", orders));
    }
}
