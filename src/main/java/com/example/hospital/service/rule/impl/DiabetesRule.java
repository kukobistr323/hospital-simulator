package com.example.hospital.service.rule.impl;

import com.example.hospital.model.Drug;
import com.example.hospital.model.HealthState;
import com.example.hospital.service.rule.Rule;

import java.util.List;

public class DiabetesRule implements Rule {

    @Override
    public HealthState apply(HealthState current, List<Drug> drugs) {
        if (current == HealthState.D && !drugs.contains(Drug.I)) {
            return HealthState.X;
        }
        return current;
    }
}
