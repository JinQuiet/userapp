package com.teamred.validator;

public interface Validator<T> {

    public ValidationResult validate(T t);

    public default String getValidationTarget() {
        return "error.general";
    }

}