package com.lm.tradeformservice.service;

import com.lm.tradeformservice.dto.TradeForm;
import com.lm.tradeformservice.dto.TradeFormStatus;
import com.lm.tradeformservice.service.impl.TradeFormServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TradeFormServiceTests {

    private TradeFormServiceImpl tradeFormService;

    @BeforeEach
    public void setup() {
        // Setup code here if needed
        tradeFormService = new TradeFormServiceImpl();
    }

    /** Tests status */
    @Test
    public void testGetRunningStatus() {
        String status = tradeFormService.getRunningStatus();
        String expectedStatus = "TradeForm Service is running";
        Assertions.assertEquals(expectedStatus, status);
    }

    /**
     * Tests the getTradeFormById method with a positive ID
     *
     * @throws Exception
     */
    @Test
    public void testGetTradeFormByPositiveId() throws Exception {

        TradeForm tradeForm = tradeFormService.getTradeFormById(1);
        Assertions.assertNotNull(tradeForm);
        Assertions.assertEquals(1, tradeForm.id());
        Assertions.assertEquals(TradeFormStatus.PENDING, tradeForm.status());
    }

    /** */
    @Test
    public void testGetTradeFormByNegativeId() {
        int negativeId = -1;
        Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> tradeFormService.getTradeFormById(negativeId));
    }
}
