package com.teamred.validator.impl;

import org.junit.Test;

import static org.junit.Assert.*;

import com.teamred.validator.ValidationResult;

public class EmailValidatorTest {

    @Test
    public void validateGetValidResult() {
        boolean expected = ValidationResult.valid().isValid();
        boolean actual = new EmailValidator().validate("example@gmail.com").isValid();
        assertEquals(expected,actual);
    }

    @Test
    public void validateGetInvalidResult() {
        boolean expected = ValidationResult.invalid("Wrong email").isValid();
        boolean actual = new EmailValidator().validate("example.gmail.com").isValid();
        assertEquals(expected,actual);
    }

    @Test
    public void validateEmptyString() {
        boolean expected = ValidationResult.invalid("Wrong email").isValid();
        boolean actual = new EmailValidator().validate("").isValid();
        assertEquals(expected,actual);
    }

    @Test
    public void validateEmptyStringWithSpaces() {
        boolean expected = ValidationResult.invalid("Wrong email").isValid();
        boolean actual = new EmailValidator().validate("            ").isValid();
        assertEquals(expected,actual);
    }

}