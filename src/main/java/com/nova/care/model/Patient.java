package com.nova.care.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class Patient {
    private String patientId;
    private String firstName;
    private String lastName;
    private String dateOfBirth;
    // STAT | URGENT | ROUTINE — clinical priority on the care request
    private String priority;
    // A patient may hold a primary plan and optionally a secondary plan
    private List<InsurancePlan> insurancePlans;
}
