package com.example.hospital.service.rule.impl;

import com.example.hospital.model.Drug;
import com.example.hospital.model.HealthState;
import com.example.hospital.service.rule.Rule;

import java.util.List;

public class FeverCureRule implements Rule {

    @Override
    public HealthState apply(HealthState current, List<Drug> drugs) {
        if (current == HealthState.F && (drugs.contains(Drug.As) || drugs.contains(Drug.P))) {
            return HealthState.H;
        }
        return current;
    }
}
