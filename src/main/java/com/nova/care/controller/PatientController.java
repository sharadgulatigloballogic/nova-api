package com.nova.care.controller;

import com.nova.care.dto.PatientSummaryDto;
import com.nova.care.repository.PatientRepository;
import com.nova.care.util.PatientUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/patients")
@CrossOrigin(origins = "*")
public class PatientController {

    private final PatientRepository patientRepository;

    public PatientController(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    @GetMapping
    public ResponseEntity<List<PatientSummaryDto>> list() {
        List<PatientSummaryDto> summaries = patientRepository.findAll().stream()
                .map(p -> PatientSummaryDto.builder()
                        .patientId(p.getPatientId())
                        .patientName(PatientUtils.fullName(p))
                        .priority(p.getPriority())
                        .planCount(p.getInsurancePlans() == null ? 0 : p.getInsurancePlans().size())
                        .build())
                .toList();
        return ResponseEntity.ok(summaries);
    }
}
