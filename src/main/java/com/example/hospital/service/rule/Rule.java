package com.example.hospital.service.rule;

import com.example.hospital.model.Drug;
import com.example.hospital.model.HealthState;

import java.util.List;

public interface Rule {
    HealthState apply(HealthState current, List<Drug> drugs);
}
