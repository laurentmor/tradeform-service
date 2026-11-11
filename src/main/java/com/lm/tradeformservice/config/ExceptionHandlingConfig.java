package com.lm.tradeformservice.config;

import com.lm.tradeformservice.exception.data.NegativeTradeFormIdException;
import com.lm.tradeformservice.exception.data.TradeFormNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * This class is a configuration class for handling exceptions in the trade form service. It is
 * annotated with {@code @ControllerAdvice}, which allows it to handle exceptions across the entire
 * application. Additionally, it can be used to define global exception handling logic, such as
 * mapping specific exceptions to HTTP response codes or custom error messages.
 * ExceptionHandlingConfig
 */
@ControllerAdvice
@Slf4j
public class ExceptionHandlingConfig {
    /**
     * Handles the TradeFormNotFoundException and returns a ResponseEntity with a NOT_FOUND status.
     * This method is annotated with {@code @ExceptionHandler}, which allows it to handle
     *
     * @param ex the TradeFormNotFoundException that was thrown.
     * @return a ResponseEntity containing the error message and a NOT_FOUND status.
     */
    @ExceptionHandler(TradeFormNotFoundException.class)
    public ResponseEntity<Object> handleTradeFormNotFoundException(TradeFormNotFoundException ex) {
        // Handle the TradeFormNotFoundException and return an appropriate response
        log.error("TradeFormNotFoundException: {}", ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    /**
     * Handles the NegativeTradeFormIdException and returns a ResponseEntity with a BAD_REQUEST
     * status. This method is annotated with {@code @ExceptionHandler}, which allows it to handle
     *
     * @param ex the NegativeTradeFormIdException that was thrown.
     * @return a ResponseEntity containing the error message and a BAD_REQUEST status.
     */
    @ExceptionHandler(NegativeTradeFormIdException.class)
    public ResponseEntity<Object> handleNegativeTradeFormIdException(
            NegativeTradeFormIdException ex) {
        // Handle the NegativeTradeFormIdException and return an appropriate response
        log.error("NegativeTradeFormIdException: {}", ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }
}
