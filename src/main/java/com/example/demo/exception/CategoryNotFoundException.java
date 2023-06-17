package com.example.demo.exception;

import com.example.demo.enums.ExceptionEnums;

public class CategoryNotFoundException extends RuntimeException {
    public CategoryNotFoundException() {
        super(ExceptionEnums.CATEGORY_NOT_FOUND_EXCEPTION.getMessage());
    }
}
