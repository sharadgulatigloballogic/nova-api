# Nova API

Spring Boot service for care-request lookup, eligibility evaluation, and
ordering-provider directory data.

## Run

```bash
mvn spring-boot:run
```

Default port: `8080`

## Key endpoints

- `GET /api/care-requests/{patientId}`
- `GET /api/eligibility/{patientId}`
- `GET /api/providers`
- `GET /api/health`
