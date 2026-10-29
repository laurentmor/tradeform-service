/**
 * The root package of the TradeForm Service application.
 *
 * <p>This package contains the Spring Boot application entry point. Its component scan includes
 * subpackages such as {@code controller}, {@code service}, {@code repository}, {@code entity},
 * {@code dto}, and {@code config}.
 *
 * <h2>Starting the application</h2>
 *
 * <p>With a JDK and a reachable PostgreSQL database configured through the application properties,
 * start the service from the project root:
 *
 * <pre>{@code
 * ./mvnw spring-boot:run
 * }</pre>
 *
 * <p>On Windows, use {@code .\mvnw.cmd spring-boot:run}. The HTTP API listens on port 8080 by
 * default.
 */
package com.lm.tradeformservice;
