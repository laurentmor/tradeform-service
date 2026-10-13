# TradeForm Service

TradeForm Service is a Spring Boot REST API and a hands-on learning project for the [Spring Boot RPG](./SPRING_BOOT_RPG_README.md). It models trade forms and is being developed incrementally as a way to learn enterprise Java and Spring Boot.

## Tech Stack

- **Java 25**
- **Spring Boot 4.1.1**
- **Spring Web** for the REST API
- **Spring Data JPA** and **Hibernate** for persistence
- **PostgreSQL** with the PostgreSQL JDBC driver
- **Maven Wrapper** for builds and tests
- **Lombok** and **Spotless** for development
- **JUnit 5** and Spring Boot Test for automated tests

GitHub Actions runs the build and tests with Java 25 and PostgreSQL 18.

## Current Functionality

The API currently exposes two GET endpoints:

| Endpoint | Response |
| --- | --- |
| `GET /api` | Service status text |
| `GET /api/tradeforms/{id}` | A trade form with the requested ID and `PENDING` status |

For example, `GET /api/tradeforms/1` returns:

```json
{
  "id": 1,
  "status": "PENDING"
}
```

The project includes a JPA entity and Spring Data repository, but the trade-form endpoint currently returns a synthetic response; it does not read trade forms from PostgreSQL yet. Negative IDs return HTTP 400.

The code is organized around a controller, service, DTO, and persistence layers. See the [Spring Boot RPG guide](./SPRING_BOOT_RPG_README.md) for the learning goals and planned progression.

## Getting Started

### Prerequisites

- JDK 25
- A reachable PostgreSQL database
- Git

Maven does not need to be installed separately; the Maven Wrapper is included.

### Configure PostgreSQL

The application and repository tests use the PostgreSQL datasource configured in `src/main/resources/application.properties`. Copy `.env.sample` to `.env` and set `datasource.url`, `datasource.username`, and `datasource.password` to credentials for a database you can access.

The database server must be running before starting the application or running the full test suite. CI starts PostgreSQL 18 automatically for its build and test job.

### Build and Test

```bash
./mvnw clean verify
```

Run only the tests with:

```bash
./mvnw test
```

On Windows PowerShell, use `.\mvnw.cmd` in place of `./mvnw`.

### Run the Service

```bash
./mvnw spring-boot:run
```

The service listens at `http://localhost:8080` by default. For example:

```bash
curl http://localhost:8080/api
curl http://localhost:8080/api/tradeforms/1
```

## CI and Build Artifacts

The GitHub Actions workflow checks formatting, then runs `clean verify` against PostgreSQL 18. It skips creation of the source archive in CI.

Local Maven lifecycle builds that reach the `test` phase (including `clean package` and `clean verify`) create a source zip named `tradeformservice-<commit-hash>.zip` at the project root. The archive excludes `target/`, `.git/`, and IDE files.

## Project Structure

```text
src/
├── main/
│   ├── java/com/lm/tradeformservice/
│   │   ├── controller/
│   │   ├── dto/
│   │   ├── entity/
│   │   ├── repository/
│   │   └── service/
│   └── resources/application.properties
├── test/java/com/lm/tradeformservice/
└── assembly/source-zip.xml
.github/workflows/release-please.yml
pom.xml
```

## Contributing

This is a personal learning project. Suggestions and code-review-style feedback are welcome via issues. The [Spring Boot RPG guide](./SPRING_BOOT_RPG_README.md) describes the learning approach.

## License

No license has been specified. All rights are reserved by default until a license is added.
