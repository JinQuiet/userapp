package com.teamred.validator.impl;

import com.teamred.validator.ValidationResult;
import com.teamred.validator.Validator;

public class AgeValidator implements Validator<String> {

    /*Newborn*/
    private static final Integer AGE_LOWER_LIMIT = 0;
    /*Methuselah, also spelled Methushael, Hebrew Bible (Old Testament) 
    patriarch whose life span as recorded in Genesis (5:27) was 969 years;
    he has survived in legend and tradition as the longest-lived human.
    */
    private static final Integer AGE_UPPER_LIMIT = 969;

    private String validationTarget;

    private ValidationResult validationResult;

    public AgeValidator() {
        this.validationTarget = "error.age";
    }

    public AgeValidator(String validationTarget) {
        this.validationTarget = validationTarget;
    }   
    
    public ValidationResult validate(String str) {

        validationResult = ValidationResult.invalid("Impossible Age Provided");

        IntegerValidator iv = new IntegerValidator();

        if (iv.validate(str).isValid()) {
            Integer age = Integer.parseInt(str);

            if ((age >= AGE_LOWER_LIMIT) && (age <= AGE_UPPER_LIMIT)) {
                validationResult = ValidationResult.valid();
            }
        }
        return validationResult;
    }

    public String getValidationTarget() {
        return validationTarget;
    }  
 
}