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

## Account Service API

| Method | Endpoint | Description |
|---|---|---|
| `POST` | `/accounts/{accountId}/transactions` | Apply a CREDIT or DEBIT transaction to an account |
| `GET` | `/accounts/{accountId}/balance` | Get an account's current balance (sum of CREDITs − sum of DEBITs) |

Example:

```bash
curl -X POST http://localhost:8081/accounts/1/transactions \
  -H "Content-Type: application/json" \
  -d '{"type":"CREDIT","amount":100.00}'

curl http://localhost:8081/accounts/1/balance
```

An account is created automatically the first time a transaction is posted to it. Requesting the balance for an account that has never had a transaction returns `404`.

## Health checks

Each service exposes:

- `GET /health` — custom endpoint that checks datasource connectivity
- `GET /actuator/health` — Spring Boot Actuator's default health endpoint

##  Data

Each service uses its own in-memory H2 database (`gateway_service` / `account_service`), reset on every restart. The H2 console is enabled at `/h2-console` on each service's port.
