package com.test.order_management.exception.handler;

import com.test.order_management.dto.response.ApiResponse;
import com.test.order_management.enums.OrderStatus;
import com.test.order_management.exception.InvalidOrderException;
import com.test.order_management.exception.InvalidStatusTransitionException;
import com.test.order_management.exception.OrderNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.Arrays;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(OrderNotFoundException.class)
    public ResponseEntity<ApiResponse<Object>> handleNotFound(OrderNotFoundException ex) {
        ApiResponse<Object> response = new ApiResponse<>(true, ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @ExceptionHandler({InvalidOrderException.class, InvalidStatusTransitionException.class})
    public ResponseEntity<ApiResponse<Object>> handleBadRequest(RuntimeException ex) {
        ApiResponse<Object> response = new ApiResponse<>(false, ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<Object>> handleValidationErrors(MethodArgumentNotValidException ex) {
        String message = ex.getBindingResult().getFieldErrors()
                .stream()
                .map(err -> err.getDefaultMessage())
                .findFirst()
                .orElse(ex.getMessage());

        ApiResponse<Object> response = new ApiResponse<>(false, message);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ApiResponse<Object>> handleEnumTypeMismatch(MethodArgumentTypeMismatchException ex) {
        String message = String.format("Invalid value '%s' for parameter '%s'. Allowed values: %s",
                ex.getValue(),
                ex.getName(),
                Arrays.toString(OrderStatus.values()));
        ApiResponse<Object> response = new ApiResponse<>(false, message);
        return ResponseEntity.badRequest().body(response);
    }

}
