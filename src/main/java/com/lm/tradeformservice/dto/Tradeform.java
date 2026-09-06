package com.lm.tradeformservice.dto;

public record TradeForm(int id, TradeFormStatus status) {
    public TradeForm {
        if (id < 0) {
            throw new IllegalArgumentException("TradeForm ID cannot be negative");
        }
    }
}
