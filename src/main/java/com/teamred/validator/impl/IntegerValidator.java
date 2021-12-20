package com.teamred.validator.impl;

import com.teamred.validator.ValidationResult;
import com.teamred.validator.Validator;

public class IntegerValidator implements Validator<String>{

    /* Name of the validation target
     * In general points to the name of the field being validated
     * userAge, userId, userEmail etc.
     * Used in the building of the "error" object by the Validation Chain
     * before pasiing errorsto the clien side
     */
    private String validationTarget;

    private ValidationResult validationResult;

    public IntegerValidator() {
        this.validationTarget = "error.integer";
    }

    public IntegerValidator(String validationTarget) {
        this.validationTarget = validationTarget;
    }

    public ValidationResult validate(String str) {

        validationResult = ValidationResult.invalid("Wrong Integer Format");
        try {
            Integer.parseInt(str);
            validationResult = ValidationResult.valid();
        } catch (NumberFormatException e) {
            //nothing goes here. result stays invalid
        }

        return validationResult;
    }

    public String getValidationTarget() {
        return validationTarget;
    }

}