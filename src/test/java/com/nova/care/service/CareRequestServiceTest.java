package com.nova.care.service;

import com.nova.care.config.NovaCareProperties;
import com.nova.care.dto.CareRequestResponse;
import com.nova.care.mapper.CareRequestMapper;
import com.nova.care.repository.InMemoryPatientRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CareRequestServiceTest {

    private CareRequestService service;

    @BeforeEach
    void setUp() {
        service = new CareRequestService(
                new InMemoryPatientRepository(),
                new CareRequestMapper(),
                new NovaCareProperties()
        );
    }

    @Test
    void getCareRequest_mapsStatPriorityFromPatientRecord() {
        CareRequestResponse response = service.getCareRequest("P-001");
        assertNotNull(response);
        assertEquals("STAT", response.getPriority());
    }

    @Test
    void getCareRequest_mapsUrgentPriorityFromPatientRecord() {
        CareRequestResponse response = service.getCareRequest("P-003");
        assertNotNull(response);
        assertEquals("URGENT", response.getPriority());
    }

    @Test
    void getCareRequest_mapsRoutinePriorityFromPatientRecord() {
        CareRequestResponse response = service.getCareRequest("P-002");
        assertNotNull(response);
        assertEquals("ROUTINE", response.getPriority());
    }
}
