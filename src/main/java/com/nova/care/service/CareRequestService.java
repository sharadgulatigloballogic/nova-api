package com.nova.care.service;

import com.nova.care.config.NovaCareProperties;
import com.nova.care.dto.CareRequestResponse;
import com.nova.care.dto.InsurancePlanDto;
import com.nova.care.mapper.CareRequestMapper;
import com.nova.care.model.Patient;
import com.nova.care.repository.PatientRepository;
import com.nova.care.util.PatientUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class CareRequestService {

    private final PatientRepository patientRepository;
    private final CareRequestMapper mapper;
    private final NovaCareProperties properties;

    public CareRequestService(
            PatientRepository patientRepository,
            CareRequestMapper mapper,
            NovaCareProperties properties
    ) {
        this.patientRepository = patientRepository;
        this.mapper = mapper;
        this.properties = properties;
    }

    public CareRequestResponse getCareRequest(String patientId) {
        Patient patient = patientRepository.findById(patientId).orElse(null);
        if (patient == null) return null;

        List<InsurancePlanDto> planDtos = mapper.toPlanDtos(patient.getInsurancePlans());

        // BUG (Scenario 1 — backend half): clinical priority is always
        // hard-coded to ROUTINE. Patient.priority (STAT / URGENT / ROUTINE)
        // is never read, so STAT/URGENT care requests look routine downstream.
        // Correct: Priority.fromString(patient.getPriority()).name()
        // or PatientUtils.clinicalPriority(patient).name()
        return CareRequestResponse.builder()
                .patientId(patient.getPatientId())
                .patientName(PatientUtils.fullName(patient))
                .dateOfBirth(patient.getDateOfBirth())
                .requestDate(LocalDate.now().toString())
                .orderingProvider(properties.getDefaultOrderingProvider())
                .priority("ROUTINE")   // BUG: should be patient.getPriority()
                .insurancePlans(planDtos)
                .build();
    }
}
