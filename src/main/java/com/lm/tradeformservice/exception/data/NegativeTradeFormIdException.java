package com.lm.tradeformservice.exception.data;

public class NegativeTradeFormIdException extends RuntimeException {

    public NegativeTradeFormIdException(final Long id) {
        super("Trade form ID cannot be negative: " + id);
    }
}
