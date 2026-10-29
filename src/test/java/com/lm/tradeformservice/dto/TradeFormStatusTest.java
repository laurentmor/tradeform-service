package com.lm.tradeformservice.dto;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/** Unit tests for the user-facing labels returned by {@link TradeFormStatus}. */
public class TradeFormStatusTest {

    /** Verifies the display label associated with the pending status. */
    @Test
    public void testGetDisplayNamePending() {
        TradeFormStatus status = TradeFormStatus.PENDING;
        String displayName = status.getDisplayName();
        Assertions.assertEquals("Pending", displayName);
    }

    /** Verifies the display label associated with the approved status. */
    @Test
    public void testGetDisplayNameApproved() {
        TradeFormStatus status = TradeFormStatus.APPROVED;
        String displayName = status.getDisplayName();
        Assertions.assertEquals("Approved", displayName);
    }

    /** Verifies the display label associated with the rejected status. */
    @Test
    public void testGetDisplayNameRejected() {
        TradeFormStatus status = TradeFormStatus.REJECTED;
        String displayName = status.getDisplayName();
        Assertions.assertEquals("Rejected", displayName);
    }
}
