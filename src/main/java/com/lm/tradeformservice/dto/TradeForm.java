package com.lm.tradeformservice.dto;

/**
 * Immutable API representation of a trade form.
 *
 * <p>The current service returns a synthetic instance with {@link TradeFormStatus#PENDING}; this
 * record is also suitable as a response type when persistence-backed retrieval is implemented.
 *
 * @param id non-negative trade-form identifier
 * @param status current lifecycle status
 * @see TradeFormStatus
 */
public record TradeForm(long id, TradeFormStatus status) {

    /**
     * Validates the identifier when a trade-form response is constructed.
     *
     * @param id non-negative trade-form identifier
     * @param status lifecycle status
     * @throws IllegalArgumentException if {@code id} is negative
     */
    public TradeForm {
        if (id < 0) {
            throw new IllegalArgumentException("TradeForm ID cannot be negative");
        }
    }
}
