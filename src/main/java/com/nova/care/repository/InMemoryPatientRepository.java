package com.nova.care.repository;

import com.nova.care.model.InsurancePlan;
import com.nova.care.model.Patient;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public class InMemoryPatientRepository implements PatientRepository {

    private static final Map<String, Patient> PATIENTS = Map.of(
        "P-001", new Patient(
            "P-001", "Jane", "Doe", "1985-04-12",
            "STAT",
            List.of(
                new InsurancePlan("IP-001", "BlueCross BlueShield", "BC-123456", "PRIMARY",  "GRP-88001"),
                new InsurancePlan("IP-002", "Aetna",                "AE-789012", "SECONDARY", "GRP-44502")
            )
        ),
        "P-002", new Patient(
            "P-002", "Robert", "Chen", "1972-11-30",
            "ROUTINE",
            List.of(
                new InsurancePlan("IP-003", "United Healthcare", "UH-456789", "PRIMARY", "GRP-22301")
            )
        ),
        "P-003", new Patient(
            "P-003", "Amelia", "Torres", "1990-07-08",
            "URGENT",
            List.of(
                new InsurancePlan("IP-004", "Cigna", "CG-112233", "PRIMARY", "GRP-55010")
            )
        )
    );

    @Override
    public Optional<Patient> findById(String patientId) {
        return Optional.ofNullable(PATIENTS.get(patientId));
    }

    @Override
    public List<Patient> findAll() {
        return new ArrayList<>(PATIENTS.values());
    }

    @Override
    public boolean existsById(String patientId) {
        return PATIENTS.containsKey(patientId);
    }
}
