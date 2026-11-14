/**
 * Domain exceptions raised when trade-form data cannot be retrieved or an identifier is invalid.
 *
 * <p>{@link com.lm.tradeformservice.exception.data.NegativeTradeFormIdException} represents an
 * invalid negative identifier, while {@link
 * com.lm.tradeformservice.exception.data.TradeFormNotFoundException} represents a missing persisted
 * trade form. The web layer maps these exceptions to HTTP 400 and HTTP 404 responses, respectively.
 */
package com.lm.tradeformservice.exception.data;
