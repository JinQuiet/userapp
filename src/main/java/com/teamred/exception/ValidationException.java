package com.teamred.exception;

public class ValidationException extends RuntimeException{
    private static final long serialVersionUID = 2622762422881248342L;

    public ValidationException(String message, Throwable cause) {
        super(message, cause);
    }
}