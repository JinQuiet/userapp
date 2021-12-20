package com.teamred.exception;

public class ValidationException extends RuntimeException {

    private final static String validationExceptionMessage = "ValidationException";

    public ValidationException() {
        super(validationExceptionMessage);
    }

    public ValidationException(String message) {
        super(validationExceptionMessage.concat(" :: ").concat(message));
    }

    public ValidationException (String message, Throwable cause) {
        super(validationExceptionMessage.concat(" :: ").concat(message), cause);
    }

    public ValidationException(Throwable cause) {
        super(validationExceptionMessage, cause);
    }
}
