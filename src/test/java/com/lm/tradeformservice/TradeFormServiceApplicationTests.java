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
 * Tests for {@link TradeFormServiceApplication}.
 * 
 * Tests the Spring Boot application entry point and class structure.
 */
class TradeFormServiceApplicationTests {

	@Test
	@DisplayName("Application class should have SpringBootApplication annotation")
	void applicationClassIsSpringBootApplication() {
		assertTrue(TradeFormServiceApplication.class.isAnnotationPresent(SpringBootApplication.class),
				"TradeFormServiceApplication should be annotated with @SpringBootApplication");
	}

	@Test
	@DisplayName("Application class should define main method")
	void applicationDefinesMainMethod() throws NoSuchMethodException {
		Method mainMethod = TradeFormServiceApplication.class.getMethod("main", String[].class);
		assertNotNull(mainMethod, "TradeFormServiceApplication should define a main method");
	}

	@Test
	@DisplayName("Main method should invoke SpringApplication.run()")
	void mainStartsSpringApplication() {
		String[] args = {"--test"};
		try (MockedStatic<SpringApplication> springApplication = mockStatic(SpringApplication.class)) {
			TradeFormServiceApplication.main(args);
			springApplication.verify(() -> SpringApplication.run(TradeFormServiceApplication.class, args));
		}
	}
}
