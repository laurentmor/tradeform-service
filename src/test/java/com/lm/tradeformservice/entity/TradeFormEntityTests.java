package com.lm.tradeformservice.entity;

import org.junit.jupiter.api.Test;

import com.lm.tradeformservice.dto.TradeFormStatus;


public class TradeFormEntityTests {

    @Test 
    public void testEntityCreationCorrect(){
        TradeFormEntity entity = new TradeFormEntity(1, TradeFormStatus.PENDING);
        assert entity.getId() == 1;
        assert entity.getStatus() == TradeFormStatus.PENDING;
    }
    
}