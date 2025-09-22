package com.example.hospital.service.rule.impl;

import com.example.hospital.model.Drug;
import com.example.hospital.model.HealthState;
import com.example.hospital.service.rule.Rule;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class HealthySideEffectRuleTest {

    private final Rule rule = new HealthySideEffectRule();

    @Test
    void healthyGetsFeverWhenInsulinAndAntibioticTogether() {
        var out = rule.apply(HealthState.H, List.of(Drug.I, Drug.An));
        assertEquals(HealthState.F, out);
    }

    @Test
    void onlyOneDrug_noChange() {
        assertEquals(HealthState.H, rule.apply(HealthState.H, List.of(Drug.I)));
        assertEquals(HealthState.H, rule.apply(HealthState.H, List.of(Drug.An)));
    }
}