package com.nova.care.util;

public final class ValidationUtils {

    private ValidationUtils() {}

    public static boolean isBlank(String value) {
        return value == null || value.trim().isEmpty();
    }

    public static String requireNonBlank(String value, String fieldName) {
        if (isBlank(value)) {
            throw new IllegalArgumentException(fieldName + " must not be blank");
        }
        return value.trim();
    }

    public static boolean isValidPatientId(String patientId) {
        return patientId != null && patientId.matches("^P-\\d{3,}$");
    }
}
