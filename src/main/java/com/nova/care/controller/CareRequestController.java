package com.nova.care.controller;

import com.nova.care.dto.CareRequestResponse;
import com.nova.care.service.CareRequestService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/care-requests")
@CrossOrigin(origins = "*")
public class CareRequestController {

    private final CareRequestService careRequestService;

    public CareRequestController(CareRequestService careRequestService) {
        this.careRequestService = careRequestService;
    }

    /**
     * GET /api/care-requests/{patientId}
     *
     * Returns care-request details including clinical priority and all
     * insurance plans for the patient.
     * Contract: response.insurancePlans is an array (may contain PRIMARY and SECONDARY).
     * Contract: response.priority is STAT | URGENT | ROUTINE from the patient record.
     */
    @GetMapping("/{patientId}")
    public ResponseEntity<CareRequestResponse> getCareRequest(@PathVariable String patientId) {
        CareRequestResponse response = careRequestService.getCareRequest(patientId);
        if (response == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(response);
    }
}
