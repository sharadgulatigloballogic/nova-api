package com.nova.care.service;

import com.nova.care.dto.ProviderSummaryDto;
import com.nova.care.model.OrderingProvider;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class ProviderService {

    private static final Map<String, OrderingProvider> PROVIDERS = Map.of(
        "PRV-100", new OrderingProvider("PRV-100", "Sarah", "Kim", "Internal Medicine", "1234567890"),
        "PRV-200", new OrderingProvider("PRV-200", "James", "Patel", "Cardiology", "0987654321")
    );

    public Optional<ProviderSummaryDto> findById(String providerId) {
        return Optional.ofNullable(PROVIDERS.get(providerId)).map(this::toDto);
    }

    public List<ProviderSummaryDto> listAll() {
        return PROVIDERS.values().stream().map(this::toDto).toList();
    }

    public ProviderSummaryDto defaultOrderingProvider() {
        return toDto(PROVIDERS.get("PRV-100"));
    }

    private ProviderSummaryDto toDto(OrderingProvider provider) {
        return ProviderSummaryDto.builder()
                .providerId(provider.getProviderId())
                .displayName("Dr. " + provider.getFirstName() + " " + provider.getLastName())
                .specialty(provider.getSpecialty())
                .npi(provider.getNpi())
                .build();
    }
}
