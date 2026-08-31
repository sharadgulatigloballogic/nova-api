package com.nova.care.repository;

import com.nova.care.model.Patient;

import java.util.List;
import java.util.Optional;

public interface PatientRepository {

    Optional<Patient> findById(String patientId);

    List<Patient> findAll();

    boolean existsById(String patientId);
}
