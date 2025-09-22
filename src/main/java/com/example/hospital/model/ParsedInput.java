package com.example.hospital.model;

import java.util.List;

public record ParsedInput(
        List<String> patientTokens,
        List<String> drugTokens
) {
}
