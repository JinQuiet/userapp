package com.teamred.exception;

public class EmailValidationException extends ValidationException{

    private final static String integerValidationExceptionMessage = "E-Mail validation failed";

    public EmailValidationException() {
        super(integerValidationExceptionMessage);
    }

    public EmailValidationException(String message) {
        super(integerValidationExceptionMessage.concat(" :: ").concat(message));
    }

    public EmailValidationException (String message, Throwable cause) {
        super(integerValidationExceptionMessage.concat(" :: ").concat(message), cause);
    }

    public EmailValidationException(Throwable cause) {
        super(integerValidationExceptionMessage, cause);
    }    
    
}
