package com.example.demo.exception;

import com.example.demo.enums.ExceptionEnums;

public class DriverNotFoundException extends RuntimeException {
    public DriverNotFoundException() {
        super(ExceptionEnums.DRIVER_NOT_FOUND_EXCEPTION.getMessage());
    }
}
