package com.lm.tradeformservice.config;

import com.lm.tradeformservice.exception.data.NegativeTradeFormIdException;
import com.lm.tradeformservice.exception.data.TradeFormNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Maps trade-form domain exceptions to HTTP responses across the application.
 *
 * <p>Missing trade forms produce HTTP 404 responses and negative identifiers produce HTTP 400
 * responses. Both response bodies contain the exception message as plain text.
 */
@ControllerAdvice
@Slf4j
public class ExceptionHandlingConfig {

    /** Creates the global trade-form exception handler. */
    public ExceptionHandlingConfig() {}

    /**
     * Converts a missing trade-form exception to an HTTP 404 response.
     *
     * @param ex the exception describing the missing trade form
     * @return HTTP 404 with the exception message as plain text
     */
    @ExceptionHandler(TradeFormNotFoundException.class)
    public ResponseEntity<Object> handleTradeFormNotFoundException(TradeFormNotFoundException ex) {
        log.warn("TradeFormNotFoundException: {}", ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    /**
     * Converts an invalid negative identifier exception to an HTTP 400 response.
     *
     * @param ex the exception describing the invalid identifier
     * @return HTTP 400 with the exception message as plain text
     */
    @ExceptionHandler(NegativeTradeFormIdException.class)
    public ResponseEntity<Object> handleNegativeTradeFormIdException(
            NegativeTradeFormIdException ex) {
        // Handle the NegativeTradeFormIdException and return an appropriate response
        log.warn("NegativeTradeFormIdException: {}", ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }
}
