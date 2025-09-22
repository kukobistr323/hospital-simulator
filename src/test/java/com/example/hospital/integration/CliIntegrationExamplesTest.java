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

class CliIntegrationExamplesTest {

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

    private static String trimLines(String s) {
        return s.replace("\r\n", "\n").trim();
    }

    @Test
    void example1_diabeticsNoDrugs() {
        var app = newApp();
        int exit = app.run(new String[]{"D,D"});
        assertEquals(0, exit);
        assertEquals("F:0,H:0,D:0,T:0,X:2", trimLines(outContent.toString()));
        assertTrue(errContent.toString().isBlank());
    }

    @Test
    void example2_feverWithParacetamol() {
        var app = newApp();
        int exit = app.run(new String[]{"F", "P"});
        assertEquals(0, exit);
        assertEquals("F:0,H:1,D:0,T:0,X:0", trimLines(outContent.toString()));
        assertTrue(errContent.toString().isBlank());
    }

    @Test
    void example3_tbFeverDiabetes_withAntibioticAndInsulin() {
        var app = newApp();
        int exit = app.run(new String[]{"T,F,D", "An,I"});
        assertEquals(0, exit);
        assertEquals("F:2,H:0,D:1,T:0,X:0", trimLines(outContent.toString()));
        assertTrue(errContent.toString().isBlank());
    }
}
