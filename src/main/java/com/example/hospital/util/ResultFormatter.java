package com.example.hospital.util;

import com.example.hospital.model.HealthState;

import java.util.EnumMap;
import java.util.List;

public final class ResultFormatter {

    public static String format(List<HealthState> patients) {
        EnumMap<HealthState, Integer> counts = new EnumMap<>(HealthState.class);
        for (HealthState state : patients) {
            counts.put(state, counts.getOrDefault(state, 0) + 1);
        }
        return String.format("F:%d,H:%d,D:%d,T:%d,X:%d",
                counts.getOrDefault(HealthState.F, 0),
                counts.getOrDefault(HealthState.H, 0),
                counts.getOrDefault(HealthState.D, 0),
                counts.getOrDefault(HealthState.T, 0),
                counts.getOrDefault(HealthState.X, 0));
    }

    private ResultFormatter() {
    }
}
