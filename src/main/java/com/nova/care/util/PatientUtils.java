package com.nova.care.util;

import com.nova.care.enums.Priority;
import com.nova.care.model.InsurancePlan;
import com.nova.care.model.Patient;

import java.util.List;

public final class PatientUtils {

    private PatientUtils() {}

    public static String fullName(Patient patient) {
        if (patient == null) {
            return "";
        }
        return patient.getFirstName() + " " + patient.getLastName();
    }

    public static Priority clinicalPriority(Patient patient) {
        if (patient == null) {
            return Priority.ROUTINE;
        }
        return Priority.fromString(patient.getPriority());
    }

    public static long secondaryPlanCount(Patient patient) {
        if (patient == null || patient.getInsurancePlans() == null) {
            return 0;
        }
        return patient.getInsurancePlans().stream()
                .filter(p -> "SECONDARY".equalsIgnoreCase(p.getType()))
                .count();
    }

    public static List<InsurancePlan> primaryPlans(Patient patient) {
        if (patient == null || patient.getInsurancePlans() == null) {
            return List.of();
        }
        return patient.getInsurancePlans().stream()
                .filter(p -> "PRIMARY".equalsIgnoreCase(p.getType()))
                .toList();
    }
}
