package com.example.hospital.validation.impl;

import com.example.hospital.validation.AbstractValidator;
import com.example.hospital.validation.context.ValidationContext;
import com.example.hospital.validation.exception.ValidationException;

public class ArgsCountValidator extends AbstractValidator {

    @Override
    protected void doValidate(ValidationContext ctx) throws ValidationException {
        int n = ctx.getArgs() == null ? 0 : ctx.getArgs().length;
        if (n < 1 || n > 2) {
            throw new ValidationException(
                    """
                            Usage: java -jar hospital-simulator.jar <patients> [drugs]
                                <patients>: comma-separated in {F,H,D,T,X}
                                [drugs]   : comma-separated in {As,An,I,P}"""
            );
        }
    }
}
