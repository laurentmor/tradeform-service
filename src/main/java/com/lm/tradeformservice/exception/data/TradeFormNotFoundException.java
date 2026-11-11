package com.lm.tradeformservice.exception.data;

/**
 * This exception is thrown when a trade form with a specified ID is not found in the database.
 * TradeFormNotFoundException
 */
public final class TradeFormNotFoundException extends RuntimeException {
    /** Constructs a new exception with a message indicating the missing trade form ID. */
    public TradeFormNotFoundException(final Long id) {
        super("Trade form with ID " + id + " not found.");
    }
}
