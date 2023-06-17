package com.example.demo.exception;

import com.example.demo.enums.ExceptionEnums;

public class FoodNotFoundException extends RuntimeException {
    public FoodNotFoundException() {
        super(ExceptionEnums.FOOD_NOT_FOUND_EXCEPTION.getMessage());
    }
}
