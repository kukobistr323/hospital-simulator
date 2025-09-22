package com.example.hospital.validation.context;

import java.util.Arrays;
import java.util.List;

public class ValidationContext {
    private final String[] args;
    private List<String> patientTokens; // filled by validators
    private List<String> drugTokens;    // filled by validators

    public ValidationContext(String[] args) {
        this.args = args;
    }

    public String[] getArgs() {
        return args;
    }

    public String rawPatients() {
        return args.length >= 1 ? args[0] : null;
    }

    public String rawDrugs() {
        return args.length >= 2 ? args[1] : "";
    }

    public void setPatientTokens(List<String> tokens) {
        this.patientTokens = tokens;
    }

    public void setDrugTokens(List<String> tokens) {
        this.drugTokens = tokens;
    }

    public List<String> getPatientTokens() {
        return patientTokens;
    }

    public List<String> getDrugTokens() {
        return drugTokens;
    }

    public static List<String> splitInputString(String inputString) {
        return Arrays.stream(inputString.split(",", -1))
                .map(String::trim)
                .toList();
    }
}