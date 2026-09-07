package com.lm.tradeformservice;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mockStatic;

import java.lang.reflect.Method;

import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class TradeFormServiceApplicationTests {

	@Test
	void contextLoads() {
	}
	
	@Test
	void applicationClassIsSpringBootApplication() {
		assertTrue(TradeFormServiceApplication.class.isAnnotationPresent(SpringBootApplication.class));
	}
	
	@Test
	void applicationDefinesMainMethod() throws NoSuchMethodException {
		Method mainMethod = TradeFormServiceApplication.class.getMethod("main", String[].class);
	
		assertNotNull(mainMethod);
	}

	@Test
	void mainStartsSpringApplication() {
		String[] args = {"--test"};
		try (MockedStatic<SpringApplication> springApplication = mockStatic(SpringApplication.class)) {
			TradeFormServiceApplication.main(args);

			springApplication.verify(() -> SpringApplication.run(TradeFormServiceApplication.class, args));
		}
	}

}
