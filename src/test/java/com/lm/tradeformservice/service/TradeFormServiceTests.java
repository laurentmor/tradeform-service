package com.lm.tradeformservice.service;

import com.lm.tradeformservice.dto.TradeForm;
import com.lm.tradeformservice.dto.TradeFormStatus;
import com.lm.tradeformservice.service.impl.TradeFormServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TradeFormServiceTests {

    @Autowired private TradeFormServiceImpl tradeFormService;

    @BeforeEach
    public void setup() {
        // Setup code here if needed
        // tradeFormService = new TradeFormServiceImpl(new
        // com.lm.tradeformservice.repository.impl.TradeFormRepositoryImpl());
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

        TradeForm tradeForm = tradeFormService.getTradeFormById(1L);
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
