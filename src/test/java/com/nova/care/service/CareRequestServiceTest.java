package com.nova.care.service;

import com.nova.care.config.NovaCareProperties;
import com.nova.care.dto.CareRequestResponse;
import com.nova.care.dto.InsurancePlanDto;
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
    void includesBothPrimaryAndSecondaryPlansForDualCoveragePatient() {
        CareRequestResponse response = service.getCareRequest("P-001");

        assertNotNull(response.getInsurancePlans());
        assertEquals(2, response.getInsurancePlans().size());

        assertTrue(response.getInsurancePlans().stream()
                .anyMatch(p -> "PRIMARY".equals(p.getType()) && "BlueCross BlueShield".equals(p.getProvider())));
        assertTrue(response.getInsurancePlans().stream()
                .anyMatch(p -> "SECONDARY".equals(p.getType()) && "Aetna".equals(p.getProvider())
                        && "AE-789012".equals(p.getMemberId())));
    }

    @Test
    void includesSinglePlanForSingleCoveragePatient() {
        CareRequestResponse response = service.getCareRequest("P-002");

        assertNotNull(response.getInsurancePlans());
        assertEquals(1, response.getInsurancePlans().size());
        InsurancePlanDto plan = response.getInsurancePlans().get(0);
        assertEquals("PRIMARY", plan.getType());
        assertEquals("United Healthcare", plan.getProvider());
    }
}
