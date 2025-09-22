package com.example.hospital.service.rule.impl;

import com.example.hospital.model.Drug;
import com.example.hospital.model.HealthState;
import com.example.hospital.service.rule.Rule;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DeadlyMixRuleTest {

    private final Rule rule = new DeadlyMixRule();

    @Test
    void aspirinPlusParacetamolKillsNonDead() {
        var out = rule.apply(HealthState.F, List.of(Drug.As, Drug.P));
        assertEquals(HealthState.X, out);
    }

    @Test
    void alreadyDead_staysDeadEvenIfDeadlyMix() {
        var out = rule.apply(HealthState.X, List.of(Drug.As, Drug.P));
        assertEquals(HealthState.X, out);
    }

    @Test
    void noDeadlyMix_noChange() {
        var out = rule.apply(HealthState.F, List.of(Drug.As));
        assertEquals(HealthState.F, out);
    }
}