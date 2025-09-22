package com.example.hospital.validation;

import com.example.hospital.validation.impl.ArgsCountValidator;
import com.example.hospital.validation.impl.DrugsSyntaxValidator;
import com.example.hospital.validation.impl.PatientsSyntaxValidator;

public final class ValidationPipeline {

    public static Validator defaultChain() {
        Validator chain = new ArgsCountValidator();
        chain.linkWith(new PatientsSyntaxValidator())
                .linkWith(new DrugsSyntaxValidator());
        return chain;
    }

    private ValidationPipeline() {
    }
}
