# Modernization Plan: Java 25 Upgrade

**Project**: tradeformservice  
**Assessment report**: report-20261011220118

## Technical Framework

- **Language**: Java 25 (upgraded from Java 17)
- **Framework**: Spring Boot 4.1.1
- **Build Tool**: Maven 3.9.15
- **Database**: PostgreSQL
- **Key Dependencies**: Spring Web, Spring Data JPA, PostgreSQL JDBC, Lombok

## Overview

This plan upgrades the application's Java runtime from Java 17 to Java 25,
the latest LTS version identified by the assessment. This addresses the
assessment finding that the application is not using the latest LTS Java
version. The application uses Spring Boot 4.1.1, which will remain unchanged.
The work is limited to the runtime upgrade and the required security/CVE
review; no infrastructure provisioning or cloud deployment is included.

## Migration Impact Summary

| App | Current | Target | Assessment issue |
|---|---|---|---|
| tradeformservice | Java 17 | Java 25 | Not latest LTS |

## Open Questions & Questionnaire

- [x] Q: Should the plan include environment/infrastructure provisioning? →
  A: No — code migration only, inferred from the Java-upgrade scope and lack of
  a provisioning request.
- [x] Q: Should the plan include integration testing? →
  A: No — skip integration testing entirely.
- [x] Q: Should the plan include security scanning and CVE remediation? →
  A: Yes — include security/CVE remediation (default).
- [x] Q: Which Azure deployment target should the plan use? →
  A: No deployment — migration only, inferred from the selected assessment
  issue and lack of a deployment request.
- [x] Q: Should the plan include containerization? →
  A: No — no deployment or containerization was requested.

## Execution Results

- Java target updated from 17 to 25; Spring Boot remains at 4.1.1.
- Maven clean verification passed on Temurin Java 25.0.4.1.
- All 38 Maven tests passed. The test environment uses the local PostgreSQL
  database `tradeformdb`; repository tests are configured to use that
  datasource rather than requiring an unavailable embedded database.
- All 10 dependency CVE findings were remediated; the final scan is clean.
