package com.product.application.exception;

public class OrderException extends RuntimeException{
    public OrderException(String text){
        super(text);
    }
}
