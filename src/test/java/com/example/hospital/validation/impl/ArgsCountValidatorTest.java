package com.example.hospital.validation.impl;

import com.example.hospital.validation.context.ValidationContext;
import com.example.hospital.validation.exception.ValidationException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

class ArgsCountValidatorTest {

    @Test
    void okWithOneArg() throws Exception {
        var v = new ArgsCountValidator();
        v.validate(new ValidationContext(new String[]{"F"}));
    }

    @Test
    void okWithTwoArgs() throws Exception {
        var v = new ArgsCountValidator();
        v.validate(new ValidationContext(new String[]{"F", "As"}));
    }

    @Test
    void notOkWithZeroArgs() {
        var v = new ArgsCountValidator();
        assertThrows(ValidationException.class,
                () -> v.validate(new ValidationContext(new String[]{})));
    }

    @Test
    void notOkWithMoreThanTwoArgs() {
        var v = new ArgsCountValidator();
        assertThrows(ValidationException.class,
                () -> v.validate(new ValidationContext(new String[]{"F", "As", "extra"})));
    }
}