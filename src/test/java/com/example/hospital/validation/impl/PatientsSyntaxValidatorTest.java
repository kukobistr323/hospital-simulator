package com.example.hospital.validation.impl;

import com.example.hospital.validation.context.ValidationContext;
import com.example.hospital.validation.exception.ValidationException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class PatientsSyntaxValidatorTest {

    @Test
    void acceptsValidCodesAndTrims() throws Exception {
        var ctx = new ValidationContext(new String[]{" F , H , D "});
        new PatientsSyntaxValidator().validate(ctx);
        assertEquals(3, ctx.getPatientTokens().size());
    }

    @Test
    void rejectsEmptyList() {
        var ctx = new ValidationContext(new String[]{""});
        assertThrows(ValidationException.class,
                () -> new PatientsSyntaxValidator().validate(ctx));
    }

    @Test
    void rejectsIllegalCode() {
        var ctx = new ValidationContext(new String[]{"Z"});
        assertThrows(ValidationException.class,
                () -> new PatientsSyntaxValidator().validate(ctx));
    }

    @Test
    void rejectsEmptyToken() {
        var ctx = new ValidationContext(new String[]{"F,,"});
        assertThrows(ValidationException.class,
                () -> new PatientsSyntaxValidator().validate(ctx));
    }
}