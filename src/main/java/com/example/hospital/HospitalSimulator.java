package com.example.hospital;


import com.example.hospital.service.CliApp;
import com.example.hospital.service.DomainMapper;
import com.example.hospital.service.InputParser;
import com.example.hospital.service.SimulatorRunner;
import com.example.hospital.service.SimulatorService;
import com.example.hospital.validation.ValidationPipeline;

public class HospitalSimulator {
    public static void main(String[] args) {
        var app = new CliApp(
                new InputParser(ValidationPipeline.defaultChain()),
                new DomainMapper(),
                new SimulatorRunner(new SimulatorService())
        );
        int exit = app.run(args);
        if (exit != 0) System.exit(exit);
    }
}
