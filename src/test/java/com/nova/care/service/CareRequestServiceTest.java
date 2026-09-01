package com.nova.care.service;

import com.nova.care.config.NovaCareProperties;
import com.nova.care.dto.CareRequestResponse;
import com.nova.care.dto.InsurancePlanDto;
import com.nova.care.mapper.CareRequestMapper;
import com.nova.care.repository.InMemoryPatientRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CareRequestServiceTest {

    private CareRequestService service;

    @BeforeEach
    void setUp() {
        NovaCareProperties properties = new NovaCareProperties();
        service = new CareRequestService(
                new InMemoryPatientRepository(),
                new CareRequestMapper(),
                properties
        );
    }

    @Test
    void getCareRequest_returnsAllInsurancePlansForDualCoveragePatient() {
        CareRequestResponse response = service.getCareRequest("P-001");

        assertNotNull(response);
        List<InsurancePlanDto> plans = response.getInsurancePlans();
        assertNotNull(plans);
        assertEquals(2, plans.size());

        assertEquals("PRIMARY", plans.get(0).getType());
        assertEquals("BlueCross BlueShield", plans.get(0).getProvider());
        assertEquals("BC-123456", plans.get(0).getMemberId());

        assertEquals("SECONDARY", plans.get(1).getType());
        assertEquals("Aetna", plans.get(1).getProvider());
        assertEquals("AE-789012", plans.get(1).getMemberId());
    }

    @Test
    void getCareRequest_returnsSinglePlanForPrimaryOnlyPatient() {
        CareRequestResponse response = service.getCareRequest("P-002");

        assertNotNull(response);
        List<InsurancePlanDto> plans = response.getInsurancePlans();
        assertNotNull(plans);
        assertEquals(1, plans.size());
        assertEquals("PRIMARY", plans.get(0).getType());
    }

    @Test
    void getCareRequest_returnsNullForUnknownPatient() {
        assertNull(service.getCareRequest("P-999"));
    }
}
