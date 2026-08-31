package com.nova.care.exception;

public class PatientNotFoundException extends ApiException {

    public PatientNotFoundException(String patientId) {
        super(404, "Patient not found: " + patientId);
    }
}
