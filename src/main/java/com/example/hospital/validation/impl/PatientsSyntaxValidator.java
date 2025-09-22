package com.example.hospital.validation.impl;

import com.example.hospital.model.HealthState;
import com.example.hospital.validation.AbstractValidator;
import com.example.hospital.validation.context.ValidationContext;
import com.example.hospital.validation.exception.ValidationException;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class PatientsSyntaxValidator extends AbstractValidator {
    private static final Set<String> ALLOWED =
            Arrays.stream(HealthState.values())
                    .map(Enum::name)
                    .collect(Collectors.toUnmodifiableSet());

    @Override
    protected void doValidate(ValidationContext ctx) throws ValidationException {
        String raw = ctx.rawPatients();
        if (raw == null || raw.isBlank()) {
            throw new ValidationException("Patients list cannot be empty. Example: D,F,F");
        }
        List<String> tokens = ValidationContext.splitInputString(raw);
        if (tokens.isEmpty()) throw new ValidationException("Patients list cannot be empty.");
        if (tokens.stream().anyMatch(String::isBlank)) {
            throw new ValidationException("Patients list contains empty entries (e.g., double commas).");
        }
        for (String t : tokens) {
            if (!ALLOWED.contains(t)) {
                throw new ValidationException("Invalid patient state: '" + t + "'. Allowed: F,H,D,T,X");
            }
        }
        ctx.setPatientTokens(tokens);
    }
}
