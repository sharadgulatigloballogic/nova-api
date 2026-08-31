package com.nova.care.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class OrderingProvider {
    private String providerId;
    private String firstName;
    private String lastName;
    private String specialty;
    private String npi;
}
