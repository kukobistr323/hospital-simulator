package com.example.hospital.validation.impl;

import com.example.hospital.model.Drug;
import com.example.hospital.validation.AbstractValidator;
import com.example.hospital.validation.context.ValidationContext;
import com.example.hospital.validation.exception.ValidationException;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class DrugsSyntaxValidator extends AbstractValidator {
    private static final Set<String> ALLOWED =
            Arrays.stream(Drug.values())
                    .map(Enum::name)
                    .collect(Collectors.toUnmodifiableSet());

    @Override
    protected void doValidate(ValidationContext ctx) throws ValidationException {
        String raw = ctx.rawDrugs();
        if (raw == null || raw.isBlank()) {
            ctx.setDrugTokens(List.of()); // no drugs are valid
            return;
        }
        List<String> tokens = ValidationContext.splitInputString(raw);
        if (tokens.stream().anyMatch(String::isBlank)) {
            throw new ValidationException("Drugs list contains empty entries (e.g., double commas).");
        }
        for (String t : tokens) {
            if (!ALLOWED.contains(t)) {
                throw new ValidationException("Invalid drug code: '" + t + "'. Allowed: As,An,I,P");
            }
        }
        ctx.setDrugTokens(tokens);
    }
}
