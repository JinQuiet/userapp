package com.teamred.validator.impl;

import org.junit.Test;

import static org.junit.Assert.*;

import com.teamred.validator.ValidationResult;

public class AgeValidatorTest {

    @Test
    public void validateGetValidResult() {
        boolean expected = ValidationResult.valid().isValid();
        boolean actual = new AgeValidator().validate("123").isValid();
        assertEquals(expected,actual);
    }

    @Test
    public void validateGetInvalidResult() {
        boolean expected = ValidationResult.invalid("Illegal string").isValid();
        boolean actual = new AgeValidator().validate("").isValid();
        assertEquals(expected,actual);
    }

    @Test
    public void validateGetInvalidResultLongString() {
        boolean expected = ValidationResult.invalid("Illegal string").isValid();
        boolean actual = new AgeValidator().validate("1111111").isValid();
        assertEquals(expected,actual);
    }

    @Test
    public void validateGetInvalidResultStringWithoutChars() {
        boolean expected = ValidationResult.invalid("Illegal string").isValid();
        boolean actual = new AgeValidator().validate("      ").isValid();
        assertEquals(expected,actual);
    }
}