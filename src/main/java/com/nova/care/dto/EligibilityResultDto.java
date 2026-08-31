package com.nova.care.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class EligibilityResultDto {
    private String patientId;
    private boolean dualCoverage;
    private boolean priorAuthLikely;
    private String summary;
}
