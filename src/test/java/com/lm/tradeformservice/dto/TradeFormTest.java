package com.lm.tradeformservice.dto;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/** Unit tests for {@link TradeForm} construction and identifier validation. */
public class TradeFormTest {

    /** Verifies that construction rejects a negative identifier. */
    @Test
    public void testTradeFormWithNegativeId() {
        int negativeId = -1;
        Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> new TradeForm(negativeId, TradeFormStatus.PENDING));
    }
}
