package com.nova.care.enums;

public enum PlanType {
    PRIMARY,
    SECONDARY;

    public static PlanType fromString(String value) {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("Plan type is required");
        }
        return PlanType.valueOf(value.trim().toUpperCase());
    }
}
