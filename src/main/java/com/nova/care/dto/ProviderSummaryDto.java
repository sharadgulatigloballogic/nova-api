package com.nova.care.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class ProviderSummaryDto {
    private String providerId;
    private String displayName;
    private String specialty;
    private String npi;
}
