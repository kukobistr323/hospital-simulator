package com.example.hospital.service.rule.impl;

import com.example.hospital.model.Drug;
import com.example.hospital.model.HealthState;
import com.example.hospital.service.rule.Rule;

import java.util.List;
import java.util.Random;

public class ResurrectionRule implements Rule {
    private final Random random;

    public ResurrectionRule() {
        this(new Random());
    }

    public ResurrectionRule(Random random) {
        this.random = random;
    }

    @Override
    public HealthState apply(HealthState current, List<Drug> drugs) {
        if (current == HealthState.X && random.nextInt(1_000_000) == 0) {
            return HealthState.H;
        }
        return current;
    }
}
