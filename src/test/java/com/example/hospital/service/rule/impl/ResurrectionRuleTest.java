package com.example.hospital.service.rule.impl;

import com.example.hospital.model.HealthState;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ResurrectionRuleTest {

    // Random stub that returns 0 first → triggers resurrection
    static class ZeroOnceRandom extends Random {
        private boolean first = true;

        @Override
        public int nextInt(int bound) {
            if (first) {
                first = false;
                return 0;
            }
            return 1; // non-zero → no resurrection afterwards
        }
    }

    @Test
    void deadPatientResurrectsWhenRandomHits() {
        var rule = new ResurrectionRule(new ZeroOnceRandom());
        var out = rule.apply(HealthState.X, List.of());
        assertEquals(HealthState.H, out);
    }

    @Test
    void nonDeadNotAffected() {
        var rule = new ResurrectionRule(new ZeroOnceRandom());
        var out = rule.apply(HealthState.H, List.of());
        assertEquals(HealthState.H, out);
    }

    @Test
    void deadPatientUsuallyStaysDead() {
        var neverZero = new Random() {
            @Override
            public int nextInt(int bound) {
                return 42;
            }
        };
        var rule = new ResurrectionRule(neverZero);
        var out = rule.apply(HealthState.X, List.of());
        assertEquals(HealthState.X, out);
    }
}