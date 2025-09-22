package com.example.hospital.service;

import com.example.hospital.model.Drug;
import com.example.hospital.model.HealthState;
import com.example.hospital.model.ParsedInput;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DomainMapperTest {

    @Test
    void mapsTokensToEnums() {
        var mapper = new DomainMapper();
        var parsed = new ParsedInput(List.of("F", "H", "D"), List.of("As", "I"));
        var domain = mapper.toDomain(parsed);

        assertEquals(List.of(HealthState.F, HealthState.H, HealthState.D), domain.patients());
        assertEquals(List.of(Drug.As, Drug.I), domain.drugs());
    }
}