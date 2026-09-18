package com.lm.tradeformservice.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.lm.tradeformservice.dto.TradeFormStatus;
import org.junit.jupiter.api.Test;

/** Unit tests for the TradeFormEntity class. TradeFormEntityTests */
public class TradeFormEntityTests {

    /** Test the creation of a TradeFormEntity with a valid ID and PENDING status. */
    @Test
    public void testEntityCreationCorrectPending() {
        TradeFormEntity entity = new TradeFormEntity(1L, TradeFormStatus.PENDING);
        assertEquals(1, entity.getId());
        assertEquals(TradeFormStatus.PENDING, entity.getStatus());
    }

    /** test the creation of a TradeFormEntity with a valid ID and APPROVED status. */
    @Test
    public void testEntityCreationCorrectApproved() {
        TradeFormEntity entity = new TradeFormEntity(2L, TradeFormStatus.APPROVED);
        assertEquals(2, entity.getId());
        assertEquals(TradeFormStatus.APPROVED, entity.getStatus());
    }

    /** Test the creation of a TradeFormEntity with a valid ID and REJECTED status. */
    @Test
    public void testEntityCreationCorrectRejected() {
        TradeFormEntity entity = new TradeFormEntity(3L, TradeFormStatus.REJECTED);
        assertEquals(3, entity.getId());
        assertEquals(TradeFormStatus.REJECTED, entity.getStatus());
    }
}
