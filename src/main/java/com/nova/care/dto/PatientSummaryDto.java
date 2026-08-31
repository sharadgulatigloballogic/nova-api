package com.nova.care.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class PatientSummaryDto {
    private String patientId;
    private String patientName;
    private String priority;
    private int planCount;
}
