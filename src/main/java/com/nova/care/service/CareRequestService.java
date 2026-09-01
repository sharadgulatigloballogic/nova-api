package com.nova.care.service;

import com.nova.care.config.NovaCareProperties;
import com.nova.care.dto.CareRequestResponse;
import com.nova.care.dto.InsurancePlanDto;
import com.nova.care.mapper.CareRequestMapper;
import com.nova.care.model.InsurancePlan;
import com.nova.care.model.Patient;
import com.nova.care.repository.PatientRepository;
import com.nova.care.util.PatientUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

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

        // BUG (Scenario 2): only the first plan is mapped into a singular
        // field `insurancePlan` instead of mapping all plans into
        // `insurancePlans`. The secondary plan is silently discarded.
        InsurancePlan primary = patient.getInsurancePlans().get(0);
        InsurancePlanDto planDto = mapper.toPlanDto(primary);

        return CareRequestResponse.builder()
                .patientId(patient.getPatientId())
                .patientName(PatientUtils.fullName(patient))
                .dateOfBirth(patient.getDateOfBirth())
                .requestDate(LocalDate.now().toString())
                .orderingProvider(properties.getDefaultOrderingProvider())
                .priority(PatientUtils.clinicalPriority(patient).name())
                .insurancePlan(planDto) // BUG: singular field, only primary
                .build();
    }
}
