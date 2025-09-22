package com.example.hospital.service.rule.impl;

import com.example.hospital.model.Drug;
import com.example.hospital.model.HealthState;
import com.example.hospital.service.rule.Rule;

import java.util.List;

public class HealthySideEffectRule implements Rule {

    @Override
    public HealthState apply(HealthState current, List<Drug> drugs) {
        if (current == HealthState.H && drugs.contains(Drug.I) && drugs.contains(Drug.An)) {
            return HealthState.F;
        }
        return current;
    }
}
