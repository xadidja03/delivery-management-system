package com.example.demo.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ExceptionEnums {
        FOOD_NOT_FOUND_EXCEPTION(HttpStatus.NOT_FOUND,"Food not found!"),
        CATEGORY_NOT_FOUND_EXCEPTION(HttpStatus.NOT_FOUND,"Category not found!"),
        DRIVER_NOT_FOUND_EXCEPTION(HttpStatus.NOT_FOUND,"Driver not found!"),
        ORDER_NOT_FOUND_EXCEPTION(HttpStatus.NOT_FOUND,"Order not found!"),
        USER_NOT_FOUND_EXCEPTION(HttpStatus.NOT_FOUND,"User not found!");
        private final HttpStatus status;
        private final String message;

    }

