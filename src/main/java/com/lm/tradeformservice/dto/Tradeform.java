package com.lm.tradeformservice.dto;

public record Tradeform(int id,TradeformStatus status) {
    public Tradeform {
        if (id < 0) {
            throw new IllegalArgumentException("Tradeform ID cannot be negative");
        }
    }
}
