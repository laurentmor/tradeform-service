package com.lm.tradeformservice.dto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Tests for {@link TradeFormStatus}.
 * 
 * TradeFormStatusTest
 */
public class TradeFormStatusTest {
    /**
     * 
     */
    @Test
    public void testGetDisplayNamePending() {
        TradeFormStatus status = TradeFormStatus.PENDING;
        String displayName = status.getDisplayName();
        Assertions.assertEquals("Pending", displayName);
    }

    @Test
    public void testGetDisplayNameApproved() {
        TradeFormStatus status = TradeFormStatus.APPROVED;
        String displayName = status.getDisplayName();
        Assertions.assertEquals("Approved", displayName);
    }

    @Test
    public void testGetDisplayNameRejected() {
        TradeFormStatus status = TradeFormStatus.REJECTED;
        String displayName = status.getDisplayName();
        Assertions.assertEquals("Rejected", displayName);
    }

}
