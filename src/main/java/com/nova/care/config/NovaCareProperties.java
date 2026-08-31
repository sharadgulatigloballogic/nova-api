package com.nova.care.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "nova.care")
public class NovaCareProperties {

    private String defaultOrderingProvider = "Dr. Sarah Kim";
    private Eligibility eligibility = new Eligibility();

    public String getDefaultOrderingProvider() {
        return defaultOrderingProvider;
    }

    public void setDefaultOrderingProvider(String defaultOrderingProvider) {
        this.defaultOrderingProvider = defaultOrderingProvider;
    }

    public Eligibility getEligibility() {
        return eligibility;
    }

    public void setEligibility(Eligibility eligibility) {
        this.eligibility = eligibility;
    }

    public static class Eligibility {
        private boolean requireSecondaryCheck = true;

        public boolean isRequireSecondaryCheck() {
            return requireSecondaryCheck;
        }

        public void setRequireSecondaryCheck(boolean requireSecondaryCheck) {
            this.requireSecondaryCheck = requireSecondaryCheck;
        }
    }
}
