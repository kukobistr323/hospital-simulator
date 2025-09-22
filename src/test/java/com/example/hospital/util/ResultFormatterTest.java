package com.example.hospital.util;

import com.example.hospital.model.HealthState;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


class ResultFormatterTest {

    @Test
    void formatsInSpecifiedOrder() {
        String s = ResultFormatter.format(List.of(
                HealthState.H, HealthState.X, HealthState.F, HealthState.F
        ));
        assertEquals("F:2,H:1,D:0,T:0,X:1", s);
    }

    @Test
    void zeroCountsShown() {
        String s = ResultFormatter.format(List.of());
        assertEquals("F:0,H:0,D:0,T:0,X:0", s);
    }
}