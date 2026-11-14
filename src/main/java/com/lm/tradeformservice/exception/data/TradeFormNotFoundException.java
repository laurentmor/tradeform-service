package com.lm.tradeformservice.exception.data;

/**
 * Indicates that no persisted trade form exists for the requested identifier.
 *
 * <p>The HTTP exception handler maps this exception to a {@code 404 Not Found} response.
 */
public final class TradeFormNotFoundException extends RuntimeException {
    /**
     * Creates an exception describing the identifier that was not found.
     *
     * @param id the missing trade-form identifier
     */
    public TradeFormNotFoundException(final Long id) {
        super("Trade form with ID " + id + " not found.");
    }
}
