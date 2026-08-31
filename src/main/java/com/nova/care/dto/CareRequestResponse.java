package com.nova.care.dto;

import lombok.Builder;
import lombok.Data;

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
 * CONTRACT BUG (Scenario 2):
 * The field below is declared as a single InsurancePlanDto (singular name,
 * object type) instead of a List<InsurancePlanDto> (plural name, array type).
 * Jackson serialises it as:
 *
 *   "insurancePlan": { ... }        <-- wrong: singular key, object
 *
 * instead of:
 *
 *   "insurancePlans": [ ... ]       <-- correct: plural key, array
 *
 * Fixing this is a breaking response-shape change; Harbor Web (and any other
 * consumer adapted to the singular field) must be updated in lockstep.
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

    // BUG (Scenario 2): should be `List<InsurancePlanDto> insurancePlans`
    private InsurancePlanDto insurancePlan;
}
