package com.nova.care.service;

import com.nova.care.config.NovaCareProperties;
import com.nova.care.dto.CareRequestResponse;
import com.nova.care.mapper.CareRequestMapper;
import com.nova.care.model.InsurancePlan;
import com.nova.care.model.Patient;
import com.nova.care.repository.PatientRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class CareRequestServiceTest {

    private CareRequestService service;

    @BeforeEach
    void setUp() {
        PatientRepository repository = new PatientRepository() {
            @Override
            public Optional<Patient> findById(String patientId) {
                return switch (patientId) {
                    case "P-001" -> Optional.of(patient("P-001", "Jane", "Doe", "STAT"));
                    case "P-002" -> Optional.of(patient("P-002", "Robert", "Chen", "ROUTINE"));
                    case "P-003" -> Optional.of(patient("P-003", "Amelia", "Torres", "URGENT"));
                    default -> Optional.empty();
                };
            }

            @Override
            public List<Patient> findAll() {
                return List.of();
            }

            @Override
            public boolean existsById(String patientId) {
                return findById(patientId).isPresent();
            }
        };
        service = new CareRequestService(repository, new CareRequestMapper(), new NovaCareProperties());
    }

    @Test
    void getCareRequest_returnsStatPriorityForP001() {
        CareRequestResponse response = service.getCareRequest("P-001");
        assertNotNull(response);
        assertEquals("STAT", response.getPriority());
    }

    @Test
    void getCareRequest_returnsUrgentPriorityForP003() {
        CareRequestResponse response = service.getCareRequest("P-003");
        assertNotNull(response);
        assertEquals("URGENT", response.getPriority());
    }

    @Test
    void getCareRequest_returnsRoutinePriorityForP002() {
        CareRequestResponse response = service.getCareRequest("P-002");
        assertNotNull(response);
        assertEquals("ROUTINE", response.getPriority());
    }

    private static Patient patient(String id, String first, String last, String priority) {
        return new Patient(
                id,
                first,
                last,
                "1990-01-01",
                priority,
                List.of(new InsurancePlan("IP-1", "Provider", "M-1", "PRIMARY", "G-1"))
        );
    }
}
