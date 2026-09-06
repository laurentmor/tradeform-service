package com.lm.tradeformservice.dto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TradeFormStatusTest {
    @Test
    public void testGetDisplayNamePending() {
        TradeformStatus status = TradeformStatus.PENDING;
        String displayName = status.getDisplayName();
        Assertions.assertEquals("Pending", displayName);
    }

    @Test
    public void testGetDisplayNameApproved() {
        TradeformStatus status = TradeformStatus.APPROVED;
        String displayName = status.getDisplayName();
        Assertions.assertEquals("Approved", displayName);
    }

    @Test
    public void testGetDisplayNameRejected() {
        TradeformStatus status = TradeformStatus.REJECTED;
        String displayName = status.getDisplayName();
        Assertions.assertEquals("Rejected", displayName);
    }

}
