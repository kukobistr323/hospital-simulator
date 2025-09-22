package com.example.hospital.service.rule.impl;

import com.example.hospital.model.Drug;
import com.example.hospital.model.HealthState;
import com.example.hospital.service.rule.Rule;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DiabetesRuleTest {

    private final Rule rule = new DiabetesRule();

    @Test
    void diabeticDiesWithoutInsulin() {
        var out = rule.apply(HealthState.D, List.of(Drug.As));
        assertEquals(HealthState.X, out);
    }

    @Test
    void insulinPreventsDeath_butDoesNotCure() {
        var out = rule.apply(HealthState.D, List.of(Drug.I));
        assertEquals(HealthState.D, out);
    }

    @Test
    void nonDiabeticUnaffected() {
        var out = rule.apply(HealthState.H, List.of());
        assertEquals(HealthState.H, out);
    }
}