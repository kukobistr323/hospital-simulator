package com.example.hospital.service;

import com.example.hospital.model.Drug;
import com.example.hospital.model.HealthState;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SimulatorServiceTest {

    @Test
    void deadlyMixOverridesOtherCures() {
        var svc = new SimulatorService();
        // F would be cured by P or As, but As+P kills
        var out = svc.simulate(List.of(HealthState.F), List.of(Drug.As, Drug.P));
        assertEquals(List.of(HealthState.X), out);
    }

    @Test
    void matchesExample3CoreLogic() {
        var svc = new SimulatorService();
        var patients = List.of(HealthState.T, HealthState.F, HealthState.D);
        var drugs = List.of(Drug.An, Drug.I);

        var out = svc.simulate(patients, drugs);

        // After TB cure (T -> H) the An+I combo turns Healthy into Fever, so we end with F,F,D.
        assertEquals(List.of(HealthState.F, HealthState.F, HealthState.D), out);
    }
}