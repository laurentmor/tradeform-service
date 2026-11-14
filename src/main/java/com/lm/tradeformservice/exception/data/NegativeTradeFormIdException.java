package com.lm.tradeformservice.exception.data;

/**
 * Indicates that a trade-form lookup was requested with a negative identifier.
 *
 * <p>The HTTP exception handler maps this exception to a {@code 400 Bad Request} response.
 */
public class NegativeTradeFormIdException extends RuntimeException {

    /**
     * Creates an exception describing the invalid identifier.
     *
     * @param id the negative trade-form identifier
     */
    public NegativeTradeFormIdException(final Long id) {
        super("Trade form ID cannot be negative: " + id);
    }
}
