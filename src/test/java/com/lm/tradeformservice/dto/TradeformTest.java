package com.lm.tradeformservice.dto;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TradeformTest {
    @Test
    public void testTradeformWithNegativeId() {
        int negativeId = -1;
        Assertions.assertThrows(IllegalArgumentException.class, () -> new Tradeform(negativeId, TradeformStatus.PENDING));
    }

}
