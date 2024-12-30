package com.project.eatTogether.infrastructure.util;

public class CustomJWTException extends RuntimeException{
    public CustomJWTException(String msg) {
        super(msg);
    }
}
