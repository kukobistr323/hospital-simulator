package com.example.hospital.service;

import com.example.hospital.validation.exception.ValidationException;

public class CliApp {

    private final InputParser parser;
    private final DomainMapper mapper;
    private final SimulatorRunner runner;

    public CliApp(InputParser parser, DomainMapper mapper, SimulatorRunner runner) {
        this.parser = parser;
        this.mapper = mapper;
        this.runner = runner;
    }

    public int run(String[] args) {
        try {
            var parsed = parser.parse(args);
            var domain = mapper.toDomain(parsed);
            String output = runner.run(domain);
            System.out.println(output);
            return 0;
        } catch (ValidationException | IllegalArgumentException ve) {
            System.err.println("Input error: " + ve.getMessage());
            return 1;
        } catch (Exception e) {
            System.err.println("Unexpected error: " + e.getMessage());
            return 1;
        }
    }
}
