package com.nova.care.enums;

public enum Priority {
    STAT,
    URGENT,
    ROUTINE;

    public static Priority fromString(String value) {
        if (value == null || value.isBlank()) {
            return ROUTINE;
        }
        return Priority.valueOf(value.trim().toUpperCase());
    }
}
