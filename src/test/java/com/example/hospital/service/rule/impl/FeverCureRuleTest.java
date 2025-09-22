package com.example.hospital.service.rule.impl;

import com.example.hospital.model.Drug;
import com.example.hospital.model.HealthState;
import com.example.hospital.service.rule.Rule;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FeverCureRuleTest {

    private final Rule rule = new FeverCureRule();

    @Test
    void curesFeverWithAspirin() {
        var out = rule.apply(HealthState.F, List.of(Drug.As));
        assertEquals(HealthState.H, out);
    }

    @Test
    void curesFeverWithParacetamol() {
        var out = rule.apply(HealthState.F, List.of(Drug.P));
        assertEquals(HealthState.H, out);
    }

    @Test
    void doesNothingForNonFever() {
        var out = rule.apply(HealthState.H, List.of(Drug.As));
        assertEquals(HealthState.H, out);
    }

    @Test
    void noDrugs_noChange() {
        var out = rule.apply(HealthState.F, List.of());
        assertEquals(HealthState.F, out);
    }
}