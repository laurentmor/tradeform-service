package com.lm.tradeformservice;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mockStatic;

import java.lang.reflect.Method;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Unit tests for the Spring Boot application entry point.
 *
 * <p>Verifies the application annotation, the presence of the {@code main} method, and delegation
 * from that method to {@link SpringApplication#run(Class, String[])}.
 */
class TradeFormServiceApplicationTests {

    /** Verifies that the application class enables Spring Boot auto-configuration and scanning. */
    @Test
    @DisplayName("Application class should have SpringBootApplication annotation")
    void applicationClassIsSpringBootApplication() {
        assertTrue(
                TradeFormServiceApplication.class.isAnnotationPresent(SpringBootApplication.class),
                "TradeFormServiceApplication should be annotated with @SpringBootApplication");
    }

    /**
     * Verifies that the standard Java entry-point method is present.
     *
     * @throws NoSuchMethodException if the expected public {@code main} method is absent
     */
    @Test
    @DisplayName("Application class should define main method")
    void applicationDefinesMainMethod() throws NoSuchMethodException {
        Method mainMethod = TradeFormServiceApplication.class.getMethod("main", String[].class);
        assertNotNull(mainMethod, "TradeFormServiceApplication should define a main method");
    }

    /** Verifies that startup delegates to Spring Boot with the supplied arguments. */
    @Test
    @DisplayName("Main method should invoke SpringApplication.run()")
    void mainStartsSpringApplication() {
        String[] args = {"--test"};
        try (MockedStatic<SpringApplication> springApplication =
                mockStatic(SpringApplication.class)) {
            TradeFormServiceApplication.main(args);
            springApplication.verify(
                    () -> SpringApplication.run(TradeFormServiceApplication.class, args));
        }
    }
}
