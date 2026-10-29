package com.lm.tradeformservice.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.lm.tradeformservice.dto.TradeFormStatus;
import org.junit.jupiter.api.Test;

/** Unit tests for {@link TradeFormEntity} construction and status values. */
public class TradeFormEntityTests {

    /** Verifies that an entity preserves its ID and pending status. */
    @Test
    public void testEntityCreationCorrectPending() {
        TradeFormEntity entity = new TradeFormEntity(1L, TradeFormStatus.PENDING);
        assertEquals(1, entity.getId());
        assertEquals(TradeFormStatus.PENDING, entity.getStatus());
    }

    /** Verifies that an entity preserves its ID and approved status. */
    @Test
    public void testEntityCreationCorrectApproved() {
        TradeFormEntity entity = new TradeFormEntity(2L, TradeFormStatus.APPROVED);
        assertEquals(2, entity.getId());
        assertEquals(TradeFormStatus.APPROVED, entity.getStatus());
    }

    /** Verifies that an entity preserves its ID and rejected status. */
    @Test
    public void testEntityCreationCorrectRejected() {
        TradeFormEntity entity = new TradeFormEntity(3L, TradeFormStatus.REJECTED);
        assertEquals(3, entity.getId());
        assertEquals(TradeFormStatus.REJECTED, entity.getStatus());
    }
}
