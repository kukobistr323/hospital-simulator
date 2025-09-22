package com.example.hospital.integration;

import com.example.hospital.service.CliApp;
import com.example.hospital.service.DomainMapper;
import com.example.hospital.service.InputParser;
import com.example.hospital.service.SimulatorRunner;
import com.example.hospital.service.SimulatorService;
import com.example.hospital.validation.ValidationPipeline;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CliValidationIntegrationTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private PrintStream originalOut;
    private PrintStream originalErr;

    private CliApp newApp() {
        return new CliApp(
                new InputParser(ValidationPipeline.defaultChain()),
                new DomainMapper(),
                new SimulatorRunner(new SimulatorService())
        );
    }

    @BeforeEach
    void setUp() {
        originalOut = System.out;
        originalErr = System.err;
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }

    @AfterEach
    void tearDown() {
        System.setOut(originalOut);
        System.setErr(originalErr);
    }

    private static String norm(String s) {
        return s.replace("\r\n", "\n");
    }

    @Test
    void zeroArgs_isError() {
        var app = newApp();
        int exit = app.run(new String[]{});
        assertEquals(1, exit);
        assertTrue(norm(errContent.toString()).contains("Input error"));
        assertTrue(outContent.toString().isBlank());
    }

    @Test
    void threeArgs_isError() {
        var app = newApp();
        int exit = app.run(new String[]{"F", "As", "extra"});
        assertEquals(1, exit);
        assertTrue(norm(errContent.toString()).contains("Input error"));
    }

    @Test
    void invalidPatientCode_isError() {
        var app = newApp();
        int exit = app.run(new String[]{"Z"});
        assertEquals(1, exit);
        assertTrue(norm(errContent.toString()).contains("Invalid patient state"));
    }

    @Test
    void emptyPatients_isError() {
        var app = newApp();
        int exit = app.run(new String[]{""});
        assertEquals(1, exit);
        assertTrue(norm(errContent.toString()).contains("Patients list cannot be empty"));
    }

    @Test
    void emptyPatientToken_isError() {
        var app = newApp();
        int exit = app.run(new String[]{"F,,"});
        assertEquals(1, exit);
        assertTrue(norm(errContent.toString()).contains("contains empty entries"));
    }

    @Test
    void invalidDrugCode_isError() {
        var app = newApp();
        int exit = app.run(new String[]{"F", "ZZ"});
        assertEquals(1, exit);
        assertTrue(norm(errContent.toString()).contains("Invalid drug code"));
    }

    @Test
    void emptyDrugToken_isError() {
        var app = newApp();
        int exit = app.run(new String[]{"F", "As,,P"});
        assertEquals(1, exit);
        assertTrue(norm(errContent.toString()).contains("contains empty entries"));
    }

    @Test
    void whitespaceIsTrimmed_andSuccess() {
        var app = newApp();
        int exit = app.run(new String[]{"  F , H  ", "  As ,  I "});
        assertEquals(0, exit);
        // F is cured by As -> H; H unchanged by As+I (no An), final: H,H
        assertEquals("F:0,H:2,D:0,T:0,X:0", outContent.toString().trim());
        assertTrue(errContent.toString().isBlank());
    }
}
