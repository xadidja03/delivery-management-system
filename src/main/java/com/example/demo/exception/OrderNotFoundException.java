package com.example.demo.exception;

import com.example.demo.enums.ExceptionEnums;

public class OrderNotFoundException extends RuntimeException {
    public OrderNotFoundException() {
        super(ExceptionEnums.ORDER_NOT_FOUND_EXCEPTION.getMessage());
    }
}
