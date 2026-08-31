package com.nova.care.controller;

import com.nova.care.dto.EligibilityResultDto;
import com.nova.care.service.EligibilityService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/eligibility")
@CrossOrigin(origins = "*")
public class EligibilityController {

    private final EligibilityService eligibilityService;

    public EligibilityController(EligibilityService eligibilityService) {
        this.eligibilityService = eligibilityService;
    }

    @GetMapping("/{patientId}")
    public ResponseEntity<EligibilityResultDto> evaluate(@PathVariable String patientId) {
        return ResponseEntity.ok(eligibilityService.evaluate(patientId));
    }
}
