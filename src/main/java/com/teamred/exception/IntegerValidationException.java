package com.teamred.exception;

public class IntegerValidationException extends ValidationException{

    private final static String integerValidationExceptionMessage = "Integer validation failed";

    public IntegerValidationException() {
        super(integerValidationExceptionMessage);
    }

    public IntegerValidationException(String message) {
        super(integerValidationExceptionMessage.concat(" :: ").concat(message));
    }

    public IntegerValidationException (String message, Throwable cause) {
        super(integerValidationExceptionMessage.concat(" :: ").concat(message), cause);
    }

    public IntegerValidationException(Throwable cause) {
        super(integerValidationExceptionMessage, cause);
    }    
    
}
