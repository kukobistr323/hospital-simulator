package com.example.hospital.service;


import com.example.hospital.model.DomainInput;
import com.example.hospital.util.ResultFormatter;

public class SimulatorRunner {

    private final SimulatorService simulator;

    public SimulatorRunner(SimulatorService simulator) {
        this.simulator = simulator;
    }

    public String run(DomainInput input) {
        var resultStates = simulator.simulate(input.patients(), input.drugs());
        return ResultFormatter.format(resultStates);
    }
}
