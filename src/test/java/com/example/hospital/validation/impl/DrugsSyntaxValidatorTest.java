package com.example.hospital.validation.impl;

import com.example.hospital.validation.context.ValidationContext;
import com.example.hospital.validation.exception.ValidationException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class DrugsSyntaxValidatorTest {

    @Test
    void emptyDrugsIsValid() throws Exception {
        var ctx = new ValidationContext(new String[]{"F", ""});
        new DrugsSyntaxValidator().validate(ctx);
        assertTrue(ctx.getDrugTokens().isEmpty());
    }

    @Test
    void acceptsValidCodesAndTrims() throws Exception {
        var ctx = new ValidationContext(new String[]{"F", " As , An , I , P "});
        new DrugsSyntaxValidator().validate(ctx);
        assertEquals(4, ctx.getDrugTokens().size());
    }

    @Test
    void rejectsIllegalCode() {
        var ctx = new ValidationContext(new String[]{"F", "ZZ"});
        assertThrows(ValidationException.class,
                () -> new DrugsSyntaxValidator().validate(ctx));
    }

    @Test
    void rejectsEmptyToken() {
        var ctx = new ValidationContext(new String[]{"F", "As,,P"});
        assertThrows(ValidationException.class,
                () -> new DrugsSyntaxValidator().validate(ctx));
    }
}