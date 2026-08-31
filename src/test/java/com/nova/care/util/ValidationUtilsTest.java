package com.nova.care.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ValidationUtilsTest {

    @Test
    void acceptsValidPatientIds() {
        assertTrue(ValidationUtils.isValidPatientId("P-001"));
        assertTrue(ValidationUtils.isValidPatientId("P-1234"));
    }

    @Test
    void rejectsInvalidPatientIds() {
        assertFalse(ValidationUtils.isValidPatientId(null));
        assertFalse(ValidationUtils.isValidPatientId("001"));
        assertFalse(ValidationUtils.isValidPatientId("X-001"));
    }
}
