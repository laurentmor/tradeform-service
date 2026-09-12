package com.lm.tradeformservice.service;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.lm.tradeformservice.dto.TradeForm;
import com.lm.tradeformservice.service.impl.TradeFormServiceImpl;
import tools.jackson.core.JacksonException;
import tools.jackson.databind.json.JsonMapper;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TradeFormServiceTests {

    
    private TradeFormServiceImpl tradeFormService;
    @Mock 
    private JsonMapper jsonMapper;

    @BeforeEach 
    public void setup() {
        // Setup code here if needed
        tradeFormService = new TradeFormServiceImpl(jsonMapper);
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
        when(jsonMapper.writeValueAsString(any(TradeForm.class))).thenReturn("{\"id\":1,\"status\":\"PENDING\"}");
        
        String tradeFormJson = tradeFormService.getTradeFormById(id);
        Assertions.assertTrue(tradeFormJson.contains("\"id\":1"));
        Assertions.assertTrue(tradeFormJson.contains("\"status\":\"PENDING\""));
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
    
    @Test
    public void testGetTradeFormByIdWithJacksonException() {
        JacksonException fakeJacksonException = mock(JacksonException.class);
        when(jsonMapper.writeValueAsString(any(TradeForm.class))).thenThrow(fakeJacksonException);
         JacksonException thrown = assertThrows(JacksonException.class,
                () -> tradeFormService.getTradeFormById("1"));

                assertSame(fakeJacksonException, thrown);


    }
}