package com.lm.tradeformservice.dto;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TradeFormTest {
    @Test
    public void testTradeFormWithNegativeId() {
        int negativeId = -1;
        Assertions.assertThrows(IllegalArgumentException.class, () -> new TradeForm(negativeId, TradeFormStatus.PENDING));
    }

}
