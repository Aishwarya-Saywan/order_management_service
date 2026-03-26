package com.test.order_management.exception;

public class OrderNotFoundException extends RuntimeException{

    public OrderNotFoundException(String orderId){
        super("order with given id "+orderId+" doesnt exist");
    }
}
