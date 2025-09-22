package com.example.hospital.validation;

import com.example.hospital.validation.context.ValidationContext;
import com.example.hospital.validation.exception.ValidationException;

public abstract class AbstractValidator implements Validator {
    private Validator next;

    @Override
    public Validator linkWith(Validator next) {
        this.next = next;
        return next;
    }

    @Override
    public void validate(ValidationContext ctx) throws ValidationException {
        doValidate(ctx);
        if (next != null) next.validate(ctx);
    }

    protected abstract void doValidate(ValidationContext ctx) throws ValidationException;
}
