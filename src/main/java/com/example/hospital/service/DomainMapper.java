package com.example.hospital.service;

import com.example.hospital.model.DomainInput;
import com.example.hospital.model.Drug;
import com.example.hospital.model.HealthState;
import com.example.hospital.model.ParsedInput;

import java.util.ArrayList;
import java.util.List;

public class DomainMapper {

    public DomainInput toDomain(ParsedInput parsed) {
        List<HealthState> patients = new ArrayList<>(parsed.patientTokens().size());
        for (String p : parsed.patientTokens()) {
            patients.add(HealthState.valueOf(p));
        }

        List<Drug> drugs = new ArrayList<>(parsed.drugTokens().size());
        for (String d : parsed.drugTokens()) {
            drugs.add(Drug.valueOf(d));
        }

        return new DomainInput(patients, drugs);
    }
}
