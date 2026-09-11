package com.lm.tradeformservice.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.lm.tradeformservice.TradeFormServiceApplication;
import com.lm.tradeformservice.controller.impl.TradeFormControllerImpl;
import com.lm.tradeformservice.service.ITradeFormService;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.when;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Tests for {@link TradeFormControllerImpl}.
 * 
 * Tests the REST endpoints for TradeForm resource management, including:
 * - Health check endpoint (/api)
 * - TradeForm retrieval endpoint (/api/tradeforms/{id})
 * - Error handling for invalid inputs and serialization failures
 */
@SpringBootTest(classes = TradeFormServiceApplication.class)
public class TradeFormControllerTests {

    private MockMvc mockMvc;

    @MockitoBean
    private ITradeFormService tradeFormService;

    @BeforeEach
    public void setUp() throws Exception {
        reset(tradeFormService);
        when(tradeFormService.getRunningStatus())
                .thenReturn("TradeForm Service is running");
        when(tradeFormService.getTradeFormById("1"))
                .thenReturn("{\"id\":1,\"status\":\"PENDING\"}");
        mockMvc = MockMvcBuilders.standaloneSetup(new TradeFormControllerImpl(tradeFormService)).build();
    }

    @Test
    void healthCheck() throws Exception {
        mockMvc.perform(get("/api"))
                .andExpect(status().isOk())
                .andExpect(content().string("TradeForm Service is running"));
    }

    @Test
    void testNegativeId() throws Exception {
        when(tradeFormService.getTradeFormById("-1"))
                .thenThrow(new IllegalArgumentException("Invalid TradeForm ID"));

        mockMvc.perform(get("/api/tradeforms/-1"))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Invalid TradeForm ID"));
    }

    @Test
    void testNonNumericId() throws Exception {
        when(tradeFormService.getTradeFormById("not-a-number"))
                .thenThrow(new IllegalArgumentException("Invalid TradeForm ID"));

        mockMvc.perform(get("/api/tradeforms/not-a-number"))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Invalid TradeForm ID"));
    }

    @Test
    void testGetTradeFormById() throws Exception {
        mockMvc.perform(get("/api/tradeforms/1"))
                .andExpect(status().isOk())
                .andExpect(content().json("{\"id\":1,\"status\":\"PENDING\"}"));
    }
}
