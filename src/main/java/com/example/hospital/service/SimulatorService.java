package com.example.hospital.service;

import com.example.hospital.model.Drug;
import com.example.hospital.model.HealthState;
import com.example.hospital.service.rule.Rule;
import com.example.hospital.service.rule.impl.DeadlyMixRule;
import com.example.hospital.service.rule.impl.DiabetesRule;
import com.example.hospital.service.rule.impl.FeverCureRule;
import com.example.hospital.service.rule.impl.HealthySideEffectRule;
import com.example.hospital.service.rule.impl.ResurrectionRule;
import com.example.hospital.service.rule.impl.TuberculosisCureRule;

import java.util.ArrayList;
import java.util.List;

public class SimulatorService {

    private final List<Rule> rules = List.of(
            new DeadlyMixRule(),
            new FeverCureRule(),
            new TuberculosisCureRule(),
            new DiabetesRule(),
            new HealthySideEffectRule(),
            new ResurrectionRule()
    );

    public List<HealthState> simulate(List<HealthState> patients, List<Drug> drugs) {
        List<HealthState> result = new ArrayList<>();
        for (HealthState patient : patients) {
            HealthState state = patient;
            for (Rule rule : rules) {
                state = rule.apply(state, drugs);
            }
            result.add(state);
        }
        return result;
    }
}
