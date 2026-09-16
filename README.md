# TradeForm Service

A Spring Boot service built as the reference application for the **[Spring Boot RPG](./SPRING_BOOT_RPG_README.md)** — a progressive, quest-based learning journey for enterprise Java and Spring Boot.

`tradeform-service` is a fictional trade-form management API, inspired by enterprise financial software. It starts as a minimal REST service and evolves quest by quest toward a production-quality application, gradually introducing persistence, validation, resilience, messaging, security, and observability.

## Tech Stack

- **Java 17**
- **Spring Boot 4.1.1** (Spring Web)
- **Maven** (with the Maven Wrapper — no local Maven install required)
- **Lombok**
- **JUnit 5 / Spring Boot Test**
- **Jakarta Persistence API** (ready for upcoming persistence quests)

## Current State

The application is at **Level 1 — The REST Awakening** of the learning campaign.

Domain model:

```
TradeForm:
  - int id
  - TradeFormStatus status

TradeFormStatus:
  - PENDING
  - APPROVED
  - REJECTED
```

Current architecture:

```
HTTP Request → Controller → Service → DTO
```

Available endpoint:

```
GET /api/tradeforms/{id}
```

Example:

```
GET /api/tradeforms/1
```

```json
{
  "id": 1,
  "status": "PENDING"
}
```

See [`SPRING_BOOT_RPG_README.md`](./SPRING_BOOT_RPG_README.md) for the full campaign map, rules of the game, and the mentor prompt used to guide development quest by quest.

## Getting Started

### Prerequisites

- JDK 17+
- Git

No local Maven installation is required — the project ships with the Maven Wrapper (`mvnw` / `mvnw.cmd`).

### Build

```bash
./mvnw clean package
```

### Run

```bash
./mvnw spring-boot:run
```

The service starts on `http://localhost:8080` by default.

### Test

```bash
./mvnw test
```

## Build Artifacts

The build is configured to automatically produce a **source zip** on each build, named after the current Git commit hash, using the [`git-commit-id-maven-plugin`](https://github.com/git-commit-id/git-commit-id-maven-plugin) together with the `maven-assembly-plugin`.

After running `./mvnw clean package`, you'll find a zip at the project root:

```
tradeformservice-<commit-hash>.zip
```

This zip contains the project source (excluding `target/`, `.git/`, and IDE files) and is useful for traceable snapshots tied to a specific commit.

## Project Structure

```
tradeform-service/
├── src/
│   ├── main/java/...     # Application source
│   └── assembly/         # Assembly descriptor for the source zip
├── .mvn/wrapper/          # Maven Wrapper files
├── pom.xml
├── SPRING_BOOT_RPG_README.md   # Full learning campaign & rules
└── README.md
```

## Roadmap

The application will progressively evolve to include:

- JPA entities, repositories, and PostgreSQL persistence
- Bean Validation and business rule enforcement
- Centralized exception handling
- Transactions, Actuator, and structured logging
- Resilience patterns (retry, circuit breakers, idempotency)
- Event-driven messaging (Kafka/RabbitMQ)
- Spring Security (authentication & authorization)
- Docker, GitHub Actions, and CI/CD automation

See the [campaign map](./SPRING_BOOT_RPG_README.md#️-campaign-map) for the complete list of levels and quests.

## Contributing

This is a personal learning project. Suggestions and code-review-style feedback are welcome via issues, but the intent is to write and evolve the code by hand as part of the learning process described in `SPRING_BOOT_RPG_README.md`.

## Code of Conduct

This project follows the [Contributor Covenant Code of Conduct](./CODE_OF_CONDUCT.md).

## License

No license has been specified yet. All rights reserved by default until a license is added.