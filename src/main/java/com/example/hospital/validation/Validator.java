package com.example.hospital.validation;

import com.example.hospital.validation.context.ValidationContext;
import com.example.hospital.validation.exception.ValidationException;

public interface Validator {
    void validate(ValidationContext ctx) throws ValidationException;

    Validator linkWith(Validator next);
}
