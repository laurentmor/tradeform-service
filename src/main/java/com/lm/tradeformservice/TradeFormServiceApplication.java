package com.lm.tradeformservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Boots the TradeForm Service Spring application.
 *
 * <p>Spring component scanning starts from this package and includes the controllers, services,
 * repositories, entities, and configuration classes below it.
 */
@SpringBootApplication
public class TradeFormServiceApplication {

    /**
     * Starts the application using the supplied command-line arguments.
     *
     * @param args command-line arguments passed to Spring Boot
     */
    public static void main(String[] args) {
        SpringApplication.run(TradeFormServiceApplication.class, args);
    }
}
