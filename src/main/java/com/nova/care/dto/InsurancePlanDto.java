package com.nova.care.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class InsurancePlanDto {
    private String planId;
    private String provider;
    private String memberId;
    private String type;
    private String groupNumber;
}
