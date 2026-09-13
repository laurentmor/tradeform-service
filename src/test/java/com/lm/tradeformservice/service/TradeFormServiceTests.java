package com.lm.tradeformservice.service;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.lm.tradeformservice.dto.TradeForm;
import com.lm.tradeformservice.dto.TradeFormStatus;
import com.lm.tradeformservice.service.impl.TradeFormServiceImpl;

import tools.jackson.databind.json.JsonMapper;


@ExtendWith(MockitoExtension.class)
public class TradeFormServiceTests {

    
    private TradeFormServiceImpl tradeFormService;
    @Mock 
    private JsonMapper jsonMapper;

    @BeforeEach 
    public void setup() {
        // Setup code here if needed
        tradeFormService = new TradeFormServiceImpl();
    }

    /**
     *  Tests status
     */
    @Test 
    public void testGetRunningStatus() {
        String status = tradeFormService.getRunningStatus();
        String expectedStatus = "TradeForm Service is running";
        Assertions.assertEquals(expectedStatus, status);
    }

    /**
     * Tests the getTradeFormById method with a positive ID 
     * @throws Exception
     */
    @Test
    public void testGetTradeFormByPositiveId() throws Exception {
    
        String id = "1";
        TradeForm tradeForm = tradeFormService.getTradeFormById(id);
        Assertions.assertNotNull(tradeForm);
        Assertions.assertEquals(1, tradeForm.id());
        Assertions.assertEquals(TradeFormStatus.PENDING, tradeForm.status());
        


}
    /**
     * 
     */
    @Test
    public void testGetTradeFormByNegativeId() {
        String negativeId = "-1";
        Assertions.assertThrows(IllegalArgumentException.class, () -> tradeFormService.getTradeFormById(negativeId));
    }
    
    @Test
    public void testGetTradeFormByInvalidId() {
        String invalidId = "abc";
        Assertions.assertThrows(IllegalArgumentException.class, () -> tradeFormService.getTradeFormById(invalidId));
    }
    
    
}