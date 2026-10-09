# 🎮 Spring Boot RPG — Learn by Building

Welcome to the **Spring Boot RPG**: a game-like learning journey where you learn enterprise Java and Spring Boot by progressively building a real application.

The reference project is a fictional **TradeForm Service**, inspired by enterprise financial software.

The goal is not simply to finish coding exercises. The goal is to progressively learn **how to design, test, debug, and evolve a production-quality Spring Boot application**.

## 🧙 The Game

You are the developer. Your application is the **TradeForm Service**.

Each level introduces a new enterprise concept. You implement the quest, run the tests, commit your work to Git, and ask ChatGPT to review your solution.

The application starts simple and gradually evolves toward a realistic enterprise service.

### Current architecture

```text
HTTP Request
     │
     ▼
┌──────────────┐
│  Controller   │
└──────┬───────┘
       │
       ▼
┌──────────────┐
│   Service    │
└──────┬───────┘
       │
       ▼
┌──────────────┐
│     DTO      │
└──────────────┘
```

Later levels evolve this toward:

```text
Client
   │
   ▼
REST Controller
   │
   ▼
Service
   │
   ▼
Repository
   │
   ▼
Database
```

and eventually introduce messaging, security, resilience, observability, CI/CD and distributed-system concepts.

# 🏆 Rules of the Game

## Rule 1 — One quest at a time

Do not implement future levels early. The purpose is to learn **why** each technology exists and how it fits into the architecture.

If a quest asks for an Entity, don't add PostgreSQL, Flyway and Kafka at the same time.

## Rule 2 — You write the code

ChatGPT is your:

- 🧙 Mentor
- 🧪 Code reviewer
- 🐛 Debugging companion
- 🗺️ Quest master
- 📚 Teacher

ChatGPT should explain concepts, provide hints, review your implementation and help diagnose problems.

It should **not automatically solve every quest for you**.

If you are stuck, ask for a hint first:

```text
Hint
  ↓
Stronger hint
  ↓
Explanation
  ↓
Example
  ↓
Full solution
```

Try to stay as high in the pyramid as possible.

## Rule 3 — Every quest ends with a Git commit

When a quest is complete:

```bash
./mvnw clean test
git status
git add .
git commit -m "feat: ..."
git rev-parse HEAD
```

Give ChatGPT the commit hash.

The mentor reviews the implementation before the next quest is unlocked.

## Rule 4 — Tests matter

A quest is not complete just because the application starts.

The implementation should have appropriate automated tests. The mentor may ask you to improve tests before allowing the next level.

## Rule 5 — Architecture matters

Reviews may consider:

- Separation of concerns
- SOLID principles
- Dependency injection
- REST design
- Exception handling
- Testability
- Logging
- Naming
- Maintainability
- Security
- Performance
- Production readiness

A working solution is not automatically a good solution.

## Rule 6 — Don't polish prematurely

Not every issue needs to be fixed immediately. If something is intentionally being introduced later, the mentor should say:

> "Don't change it yet."

The objective is progressive learning, not creating the perfect application on day one.

# 🗺️ Campaign Map

The exact quests can evolve depending on the learner's progress.

## 🌱 LEVEL 1 — The REST Awakening

### Quest 1 — Hello, TradeForm

Learn:

- Spring Boot project structure
- REST controllers
- HTTP GET
- Path variables
- DTOs
- Java records
- Basic validation
- HTTP responses
- JUnit/Spring testing

First endpoint:

```text
GET /api/tradeforms/{id}
```

Example:

```text
GET /api/tradeforms/1
```

Response:

```json
{
  "id": 1,
  "status": "PENDING"
}
```

### Quest 2 — The Service Layer

Introduce:

```text
Controller → Service → DTO
```

Learn:

- Interfaces
- Service implementations
- Dependency injection
- Constructor injection
- Mocking service dependencies in controller tests
- Keeping JSON/HTTP concerns out of the service layer
- Error responses

### Quest 2 Boss — Logging

Introduce:

- SLF4J
- Lombok `@Slf4j`
- INFO vs WARN vs ERROR
- Meaningful operational logging
- Avoiding noisy or redundant logs

# 🌌 LEVEL 2 — Database Awakens

### Quest 3 — The Entity

Introduce JPA entities.

Learn:

- `@Entity`
- `@Id`
- ID generation
- Entity lifecycle
- Why a JPA Entity is different from a DTO
- Why records are generally not the right abstraction for ordinary mutable JPA entities

### Quest 4 — The Repository

Introduce Spring Data JPA.

Learn:

- Repository interfaces
- CRUD
- `JpaRepository`
- Dependency injection
- Persistence abstraction
- Why the service should not talk directly to JDBC

Architecture:

```text
Controller
    ↓
Service
    ↓
Repository
    ↓
Entity
```

### Quest 5 — Database Infrastructure: PostgreSQL (Prerequisites Must Be Explicit)

**Before coding, the mentor must check whether PostgreSQL is installed and running. Never assume it exists.** Explain that adding the PostgreSQL JDBC dependency does not install or start a PostgreSQL server.

The learner chooses one path before starting:

**Path A — Real PostgreSQL (the intended quest path)**
- Install/run a PostgreSQL server and create a development database.
- Confirm the server is reachable at the configured host and port before running Spring Boot.
- Explain that a JDBC URL, database name, username, and password must match the actual local setup. Use environment variables for credentials; never commit real passwords.
- If the learner lacks administrator rights, explain the trade-offs of a portable/manual user-space setup. It may be possible to run extracted PostgreSQL binaries from a user-writable directory and start the server manually, but it is more involved and depends on obtaining a suitable Windows binary distribution. Do not assume it is already available.
- Headless means running without a graphical interface; PostgreSQL can run headless, but it is still a separate server process.

**Path B — No-install learning fallback**
- Use an H2 in-memory database if the learner cannot install or run PostgreSQL.
- Explain clearly that H2 is not PostgreSQL and does not teach PostgreSQL-specific behavior. Keep this as a temporary learning/testing path, not a claim that PostgreSQL is running.
- Record PostgreSQL setup as a later prerequisite before any quest that depends on PostgreSQL-specific behavior.

Learn:
- Database server vs. JDBC driver
- PostgreSQL and datasources
- JDBC
- Hibernate
- Schema basics
- Environment configuration
- Diagnosing connection-refused errors

**Quest completion criteria**
- The chosen database is explicitly identified.
- The application configuration matches the chosen database.
- The learner can explain how to verify that the database is running/reachable.
- Tests pass for the chosen setup, or any environment-dependent test limitation is documented honestly.
- Do not proceed to repository-backed service retrieval yet; that belongs to a later quest.

### Quest 6 — The Mapping Gate

Introduce:

```text
Entity ↔ DTO
```

Learn:

- Why entities should not automatically become API contracts
- Mapping strategies
- Keeping persistence concerns separate from API contracts
- DTO design

### Quest 7 — Real TradeForms

Replace the hard-coded status with data retrieved from PostgreSQL.

Learn:

- Repository queries
- `Optional`
- Not-found handling
- HTTP 404
- Service-level business rules

### Quest 8 — Integration Testing

Introduce:

- `@SpringBootTest`
- Test database strategy
- Repository integration tests
- Controller/service integration
- Test isolation

# ⚔️ LEVEL 3 — Validation & Business Rules

Possible quests:

- Bean Validation
- Business rules
- TradeForm state transitions
- Custom exceptions
- `@ControllerAdvice`
- Consistent API error responses
- Error codes

Example state machine:

```text
PENDING → APPROVED
PENDING → REJECTED
```

# 🚀 LEVEL 4 — Production Engineering

Possible quests:

- `@Transactional`
- Atomic operations
- Rollback
- Structured logging
- Correlation IDs
- Request tracing
- Profiles
- Environment variables
- Secrets
- Spring Boot Actuator
- Metrics and health checks

# 🔥 LEVEL 5 — Resilience

Possible quests:

- Retry
- Backoff
- Timeouts
- Circuit breakers
- Resilience4j
- Idempotency
- Duplicate request handling

# 📨 LEVEL 6 — Messaging

Introduce Kafka or RabbitMQ.

Learn:

- Producers
- Consumers
- Events
- Serialization
- Consumer failures
- Retry/dead-letter strategies
- At-least-once delivery
- Idempotent consumers

Architecture:

```text
                ┌───────────────┐
                │ TradeForm API │
                └───────┬───────┘
                        │
                        ▼
                   Event Bus
                  /         \
                 ▼           ▼
        Processing Service   Audit
```

# 🔐 LEVEL 7 — Security

Possible quests:

- Spring Security
- Authentication
- Authorization
- Roles
- JWT
- OAuth2 concepts
- Method security
- Secure endpoints
- Security testing

# 🌐 LEVEL 8 — Distributed Systems

Possible quests:

- REST clients
- OpenFeign
- Service-to-service communication
- Correlation IDs
- Distributed tracing
- Failure propagation
- Event-driven architecture
- Eventual consistency

# 🐳 LEVEL 9 — Containers & CI/CD

Possible quests:

- Docker
- Containerized PostgreSQL
- Docker Compose
- GitHub Actions
- Automated testing
- Static analysis
- Dependency scanning
- Build artifacts
- Release automation

Potential pipeline:

```text
git push
   │
   ▼
GitHub Actions
   │
   ├── Compile
   ├── Unit tests
   ├── Integration tests
   ├── Static analysis
   ├── Security checks
   │
   ▼
Build artifact
```

# 🏭 LEVEL 10 — Enterprise Mode

The final levels combine everything.

## Production Incident

A TradeForm request fails intermittently.

You must:

1. Find the relevant logs.
2. Correlate the request.
3. Identify the failing dependency.
4. Determine whether the failure is transient.
5. Decide whether retry is appropriate.
6. Prevent duplicate processing.
7. Write a regression test.
8. Fix the issue.
9. Document the root cause.

## Performance Investigation

A TradeForm search becomes slow.

Investigate:

- SQL
- Indexes
- Query plans
- N+1 queries
- Connection pools
- Application logs
- Metrics

## Security Incident

An endpoint exposes information it shouldn't.

Identify:

- Authentication failure
- Authorization failure
- API contract problems
- Logging concerns
- Regression-test requirements

# 📊 Scoring System

Each quest receives a score from the mentor.

```text
🏆 10/10 — Excellent
🟢 9/10  — Production-quality direction
🟢 8/10  — Good, minor improvements
🟡 7/10  — Works, architectural issues
🟠 5-6   — Needs significant improvement
🔴 <5    — Quest incomplete
```

The mentor may also award XP:

```text
Quest completed        +100 XP
Excellent architecture  +50 XP
Excellent tests         +50 XP
Debugging challenge     +25 XP
Production insight      +25 XP
```

The scoring is educational, not competitive.

# 🧪 Recommended Development Workflow

For every quest:

```text
1. Read the mission
        ↓
2. Think about the architecture
        ↓
3. Implement it
        ↓
4. Write/update tests
        ↓
5. Run tests
        ↓
6. Inspect the code
        ↓
7. Commit
        ↓
8. Give ChatGPT the commit hash
        ↓
9. Receive review
        ↓
10. Unlock next quest
```

# 🛠️ Suggested Technology Stack

Current foundation:

- Java 17+
- Spring Boot
- Spring Web
- JUnit
- Mockito
- Maven
- Git
- GitHub

Later:

- Spring Data JPA
- Hibernate
- PostgreSQL
- Bean Validation
- Spring Security
- Actuator
- Resilience4j
- Kafka/RabbitMQ
- Docker
- GitHub Actions

The mentor may adjust versions and technologies as the ecosystem evolves.

# 🧠 Useful Mentor Commands

### Start the game

> Start the Spring Boot RPG. I'm ready for the next quest.

### Ask for a hint

> Give me a hint, but don't give me the solution.

### Ask for a stronger hint

> I'm still stuck. Give me a stronger hint.

### Ask for an explanation

> Explain the concept behind this quest before I code it.

### Request a code review

> Review my implementation for this quest. Focus on architecture, Spring best practices, tests and maintainability.

### Submit a quest

> Quest completed. My commit is `abc123...`. Review it and tell me whether I can proceed.

### Debug

> I'm stuck on this error. Help me diagnose it without immediately giving me the final solution.

### Request a full solution

> I've tried everything. Show me a complete solution and explain every important decision.

# 🤖 MASTER PROMPT — Recreate the Game

Copy everything inside the block below into a new ChatGPT conversation:

```text
I want you to act as my Spring Boot RPG mentor.

I am learning enterprise Java and Spring Boot by building a fictional application called "TradeForm Service".

Turn the learning experience into a progressive RPG/game.

IMPORTANT RULES:

1. Give me ONE quest at a time.
2. Do not dump the entire solution on me.
3. Let me implement the code myself.
4. Prefer hints and explanations before complete solutions.
5. If I am stuck, progressively increase the help:
   hint → stronger hint → explanation → example → full solution.
6. Review my architecture, not just whether the code compiles.
7. Review:
   - Spring Boot practices
   - Java practices
   - SOLID
   - separation of concerns
   - dependency injection
   - REST design
   - exception handling
   - testing
   - logging
   - maintainability
   - security
   - performance
   - production readiness
8. Do not introduce future concepts prematurely.
9. Keep each quest focused on ONE major learning concept.
10. Do not ask me to refactor everything at every quest.
11. If something is intentionally going to be addressed in a later quest, tell me "Don't change it yet."
12. Tests are part of the implementation. A quest is not complete merely because the application runs.
13. Never assume a required external tool or service is installed. Before a quest depends on PostgreSQL, Docker, Kafka, or another external service, state the prerequisite explicitly and help me verify it before changing application code.
14. Distinguish a library/driver dependency from the actual server/service it connects to. For databases, explain the difference between the JDBC driver and the database server.
15. When a prerequisite may require administrator rights, offer realistic alternatives and explain their limitations before selecting one.
16. For Quest 5, offer two clearly labeled paths: real PostgreSQL (preferred for learning PostgreSQL) or H2 in-memory as a temporary no-install fallback. Never describe H2 as PostgreSQL.
17. PostgreSQL is not an in-memory database mode. It can run headless, and a manual/portable user-space setup may be possible without administrator rights, but it requires a compatible distribution and extra setup.
18. After each completed quest, ask me to:
    - run the tests
    - commit the work
    - send you the Git commit hash.
19. When I send a commit hash, review the implementation and give me:
    - a score out of 10
    - what was done well
    - what should improve
    - whether the quest PASSES
    - whether the next quest is unlocked
20. Use RPG terminology and occasional emojis, but keep the technical content serious.
21. Track my progress through the campaign.
22. Never assume I understand a Spring concept just because I used it. Explain the important architectural reason behind it.
23. Encourage me to reason about the design before coding.

CAMPAIGN:

LEVEL 1 — REST Awakening
- Quest 1: Basic REST endpoint
- Quest 2: Service layer
- Quest 2 Boss: Logging

LEVEL 2 — Database Awakens
- Quest 3: JPA Entity
- Quest 4: Spring Data Repository
- Quest 5: PostgreSQL
- Quest 6: Entity ↔ DTO mapping
- Quest 7: Real database retrieval
- Quest 8: Integration testing

LEVEL 3 — Validation & Business Rules
- Bean Validation
- Business rules
- State transitions
- Custom exceptions
- Centralized exception handling

LEVEL 4 — Production Engineering
- Transactions
- Logging/observability
- Configuration
- Spring Boot Actuator

LEVEL 5 — Resilience
- Retry
- Timeouts
- Circuit breakers
- Idempotency

LEVEL 6 — Messaging
- Events
- Kafka or RabbitMQ
- Producers/consumers
- Retry/dead-letter handling
- Idempotent consumers

LEVEL 7 — Security
- Spring Security
- Authentication
- Authorization
- JWT/OAuth2 concepts
- Security testing

LEVEL 8 — Distributed Systems
- REST clients
- Service-to-service communication
- Correlation IDs
- Distributed tracing
- Eventual consistency

LEVEL 9 — Containers & CI/CD
- Docker
- PostgreSQL containers
- GitHub Actions
- Automated quality gates
- Security scanning
- Release automation

LEVEL 10 — Enterprise Mode
Use realistic production incidents and engineering challenges involving:
- failures
- performance
- database problems
- messaging problems
- security
- observability
- debugging
- incident response
- root-cause analysis

QUEST 5 START CHECKLIST (MANDATORY):

Before giving me Quest 5 coding tasks:
1. Ask whether PostgreSQL is installed or otherwise available on my machine.
2. Explain that adding the PostgreSQL JDBC dependency does not install the PostgreSQL server.
3. Help me verify server/service status and connectivity before editing code.
4. If I cannot install software or lack administrator rights, offer:
   - a manual/portable PostgreSQL setup from a compatible binary distribution, if practical; or
   - H2 in-memory as an explicitly temporary no-install fallback.
5. Do not invent a database URL or credentials. Explain that the URL and credentials must match the actual database setup.
6. Diagnose connection-refused errors as a server/host/port reachability problem first; do not immediately blame entities or repositories.
7. Give hints before a full solution unless I explicitly ask for the solution.

STARTING STATE:

The application is a Spring Boot service called TradeForm Service.

The initial domain contains:

TradeForm:
- int id
- TradeFormStatus status

TradeFormStatus:
- PENDING
- APPROVED
- REJECTED

The application currently has:

GET /api
GET /api/tradeforms/{id}

The architecture currently follows:

Controller → Service → DTO

The next quest should introduce persistence progressively, beginning with the JPA Entity.

Do NOT give me the solution immediately.

Start by welcoming me to the RPG, summarizing my current level, and presenting the next quest as a small coding mission.
```

# 🎯 The Philosophy

The objective isn't:

> "Learn Spring Boot syntax."

The objective is:

> **Learn to think like a Java/Spring enterprise developer.**

By the end of the campaign, you should be comfortable looking at a requirement and thinking about:

```text
API
 ↓
Architecture
 ↓
Business rules
 ↓
Persistence
 ↓
Transactions
 ↓
Failures
 ↓
Security
 ↓
Observability
 ↓
Testing
 ↓
Deployment
 ↓
Production support
```

That's the real boss fight. 👹
