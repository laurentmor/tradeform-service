package com.lm.tradeformservice.dto;

/**
 * Immutable API data representation of a trade form.
 *
 * <p>The service layer maps between this record and persisted entities. This record does not
 * validate its component values.
 *
 * @param id trade-form identifier
 * @param status current lifecycle status
 * @see TradeFormStatus
 */
public record TradeForm(long id, TradeFormStatus status) {}
