package com.example.hospital.service;

import com.example.hospital.model.DomainInput;
import com.example.hospital.model.HealthState;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SimulatorRunnerTest {

    @Test
    void formatsFinalResult() {
        var runner = new SimulatorRunner(new SimulatorService());
        var input = new DomainInput(List.of(HealthState.D, HealthState.D), List.of());
        var out = runner.run(input);
        assertEquals("F:0,H:0,D:0,T:0,X:2", out);
    }
}