package com.example.hospital.model;

import java.util.List;

public record DomainInput(
        List<HealthState> patients,
        List<Drug> drugs
) {
}
