package com.example.hospital.service;

import com.example.hospital.validation.exception.ValidationException;
import com.example.hospital.validation.impl.ArgsCountValidator;
import com.example.hospital.validation.impl.DrugsSyntaxValidator;
import com.example.hospital.validation.impl.PatientsSyntaxValidator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class InputParserTest {

    @Test
    void parsesOk() throws Exception {
        var chain = new ArgsCountValidator();
        chain.linkWith(new PatientsSyntaxValidator())
                .linkWith(new DrugsSyntaxValidator());
        var parser = new InputParser(chain);

        var out = parser.parse(new String[]{"F,H", "As"});
        assertEquals(2, out.patientTokens().size());
        assertEquals(1, out.drugTokens().size());
    }

    @Test
    void throwsValidationExceptionOnBadArgs() {
        var chain = new ArgsCountValidator();
        chain.linkWith(new PatientsSyntaxValidator());
        var parser = new InputParser(chain);

        assertThrows(ValidationException.class,
                () -> parser.parse(new String[]{""}));
    }
}