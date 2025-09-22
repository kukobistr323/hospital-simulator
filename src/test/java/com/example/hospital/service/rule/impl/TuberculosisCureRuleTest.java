package com.example.hospital.service.rule.impl;

import com.example.hospital.model.Drug;
import com.example.hospital.model.HealthState;
import com.example.hospital.service.rule.Rule;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TuberculosisCureRuleTest {

    private final Rule rule = new TuberculosisCureRule();

    @Test
    void curesTBWithAntibiotic() {
        var out = rule.apply(HealthState.T, List.of(Drug.An));
        assertEquals(HealthState.H, out);
    }

    @Test
    void noAntibiotic_noChange() {
        var out = rule.apply(HealthState.T, List.of(Drug.As));
        assertEquals(HealthState.T, out);
    }
}