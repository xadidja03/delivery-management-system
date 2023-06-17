package com.example.demo.exception;
import com.example.demo.enums.ExceptionEnums;

public class AppUserNotFoundException extends RuntimeException{
    public AppUserNotFoundException() {
        super(ExceptionEnums.USER_NOT_FOUND_EXCEPTION.getMessage());
    }
}
