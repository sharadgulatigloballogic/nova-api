package com.nova.care.controller;

import com.nova.care.dto.ProviderSummaryDto;
import com.nova.care.service.ProviderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/providers")
@CrossOrigin(origins = "*")
public class ProviderController {

    private final ProviderService providerService;

    public ProviderController(ProviderService providerService) {
        this.providerService = providerService;
    }

    @GetMapping
    public ResponseEntity<List<ProviderSummaryDto>> list() {
        return ResponseEntity.ok(providerService.listAll());
    }

    @GetMapping("/{providerId}")
    public ResponseEntity<ProviderSummaryDto> get(@PathVariable String providerId) {
        return providerService.findById(providerId)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
