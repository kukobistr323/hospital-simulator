package com.example.hospital.service;

import com.example.hospital.model.ParsedInput;
import com.example.hospital.validation.Validator;
import com.example.hospital.validation.context.ValidationContext;
import com.example.hospital.validation.exception.ValidationException;

public class InputParser {

    private final Validator validatorChain;

    public InputParser(Validator validatorChain) {
        this.validatorChain = validatorChain;
    }

    public ParsedInput parse(String[] args) throws ValidationException {
        ValidationContext ctx = new ValidationContext(args);
        validatorChain.validate(ctx);
        return new ParsedInput(ctx.getPatientTokens(), ctx.getDrugTokens());
    }
}
