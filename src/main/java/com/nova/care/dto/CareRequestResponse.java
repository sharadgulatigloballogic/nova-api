package com.nova.care.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * API contract (documented in openapi.yaml):
 *
 *   GET /api/care-requests/{patientId}
 *   {
 *     "patientId":    "P-001",
 *     "patientName":  "Jane Doe",
 *     "dateOfBirth":  "1985-04-12",
 *     "requestDate":  "2026-08-28",
 *     "orderingProvider": "Dr. Sarah Kim",
 *     "priority": "STAT",
 *     "insurancePlans": [           <-- array; patients can have primary + secondary
 *       { "planId": "...", "provider": "...", "memberId": "...",
 *         "type": "PRIMARY",  "groupNumber": "..." },
 *       { "planId": "...", "provider": "...", "memberId": "...",
 *         "type": "SECONDARY", "groupNumber": "..." }
 *     ]
 *   }
 *
 */
@Data
@Builder
public class CareRequestResponse {
    private String patientId;
    private String patientName;
    private String dateOfBirth;
    private String requestDate;
    private String orderingProvider;

    // Clinical priority: STAT | URGENT | ROUTINE
    private String priority;

    private List<InsurancePlanDto> insurancePlans;
}
