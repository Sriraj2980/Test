# event-ledger-prod

## Services

| Service | Port | Description |
|---|---|---|
| `gateway-service` | 8080 | Entry point service |
| `account-service` | 8081 | Account domain service |

Both services are Spring Boot 3.x / Java 17 / Maven projects with Spring Web, Spring Data JPA, H2 (in-memory), Validation, and Actuator.

## Running locally

Each service runs independently:

```bash
cd account-service
mvn spring-boot:run
```

```bash
cd gateway-service
mvn spring-boot:run
```

## Health checks

Each service exposes:

- `GET /health` — custom endpoint that checks datasource connectivity
- `GET /actuator/health` — Spring Boot Actuator's default health endpoint

## Data

Each service uses its own in-memory H2 database (`gateway_service` / `account_service`), reset on every restart. The H2 console is enabled at `/h2-console` on each service's port.
