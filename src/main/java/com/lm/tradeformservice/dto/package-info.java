/**
 * Data transfer objects and enumerated values used by the TradeForm Service API.
 *
 * <p>{@link com.lm.tradeformservice.dto.TradeForm} is the response representation of a trade form,
 * {@link com.lm.tradeformservice.dto.TradeFormStatus} defines its supported lifecycle statuses, and
 * {@link com.lm.tradeformservice.dto.ErrorResponse} carries a client-facing error message.
 *
 * <h2>Creating a response DTO</h2>
 *
 * <pre>{@code
 * TradeForm response = new TradeForm(42L, TradeFormStatus.PENDING);
 * }</pre>
 *
 * <p>Serializing this record with Spring's JSON support produces:
 *
 * <pre>{@code
 * {
 *   "id": 42,
 *   "status": "PENDING"
 * }
 * }</pre>
 *
 * <p>The {@link com.lm.tradeformservice.dto.TradeForm} constructor rejects negative IDs with an
 * {@link java.lang.IllegalArgumentException}. Use {@link
 * com.lm.tradeformservice.dto.TradeFormStatus#getDisplayName()} when a human-readable status label
 * is needed.
 */
package com.lm.tradeformservice.dto;
