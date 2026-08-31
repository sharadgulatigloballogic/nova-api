package com.nova.care.service;

import com.nova.care.config.NovaCareProperties;
import com.nova.care.dto.EligibilityResultDto;
import com.nova.care.exception.PatientNotFoundException;
import com.nova.care.model.Patient;
import com.nova.care.repository.PatientRepository;
import com.nova.care.util.PatientUtils;
import org.springframework.stereotype.Service;

@Service
public class EligibilityService {

    private final PatientRepository patientRepository;
    private final NovaCareProperties properties;

    public EligibilityService(PatientRepository patientRepository, NovaCareProperties properties) {
        this.patientRepository = patientRepository;
        this.properties = properties;
    }

    public EligibilityResultDto evaluate(String patientId) {
        Patient patient = patientRepository.findById(patientId)
                .orElseThrow(() -> new PatientNotFoundException(patientId));

        boolean dual = PatientUtils.secondaryPlanCount(patient) > 0;
        boolean priorAuthLikely = dual || "STAT".equalsIgnoreCase(patient.getPriority());

        String summary;
        if (dual) {
            summary = "Primary + Secondary coverage on file.";
        } else if (properties.getEligibility().isRequireSecondaryCheck()) {
            summary = "Primary coverage only — verify secondary before submitting.";
        } else {
            summary = "Primary coverage on file.";
        }

        return EligibilityResultDto.builder()
                .patientId(patientId)
                .dualCoverage(dual)
                .priorAuthLikely(priorAuthLikely)
                .summary(summary)
                .build();
    }
}
