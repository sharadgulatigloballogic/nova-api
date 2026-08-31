package com.nova.care.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class InsurancePlan {
    private String planId;
    private String provider;
    private String memberId;
    // PRIMARY or SECONDARY
    private String type;
    private String groupNumber;
}
