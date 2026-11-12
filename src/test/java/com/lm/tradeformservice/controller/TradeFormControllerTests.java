package com.lm.tradeformservice.controller;

import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.lm.tradeformservice.config.ExceptionHandlingConfig;
import com.lm.tradeformservice.controller.impl.TradeFormControllerImpl;
import com.lm.tradeformservice.dto.TradeForm;
import com.lm.tradeformservice.dto.TradeFormStatus;
import com.lm.tradeformservice.exception.data.NegativeTradeFormIdException;
import com.lm.tradeformservice.exception.data.TradeFormNotFoundException;
import com.lm.tradeformservice.service.ITradeFormService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

/**
 * MVC tests for {@link TradeFormControllerImpl}.
 *
 * <p>Uses Spring's WebMvcTest infrastructure with a mocked service to verify
 * HTTP responses and centralized exception handling.
 */@WebMvcTest(controllers = TradeFormControllerImpl.class)
@Import(ExceptionHandlingConfig.class)
public class TradeFormControllerTests {

    /** Mock MVC dispatcher configured for the controller under test. */
    @Autowired private MockMvc mockMvc;

    /** Mock service used to isolate HTTP behavior from business logic. */
    @MockitoBean private ITradeFormService tradeFormService;

    /** Resets the service mock and stubs the expected successful responses. */
    @BeforeEach
    public void setUp() {
        
        when(tradeFormService.getRunningStatus()).thenReturn("TradeForm Service is running");
        when(tradeFormService.getTradeFormById(1L))
                .thenReturn(new TradeForm(1L, TradeFormStatus.PENDING));
    }

    /**
     * Verifies that {@code GET /api} returns HTTP 200 and the service status text.
     *
     * @throws Exception if the MockMvc request fails
     */
    @Test
    void healthCheck() throws Exception {
        mockMvc.perform(get("/api"))
                .andExpect(status().isOk())
                .andExpect(content().string("TradeForm Service is running"));
    }

    /**
     * Verifies that a negative identifier is translated to HTTP 400 with the exception message.
     *
     * @throws Exception if the MockMvc request fails
     */
    @Test
    void testNegativeId() throws Exception {
        when(tradeFormService.getTradeFormById(-1))
                .thenThrow(new NegativeTradeFormIdException(-1L));

        mockMvc.perform(get("/api/tradeforms/-1"))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Trade form ID cannot be negative: -1"));
    }

    /**
     * Verifies that {@code GET /api/tradeforms/1} returns the expected trade-form JSON.
     *
     * @throws Exception if the MockMvc request fails
     */
    @Test
    void testGetTradeFormById() throws Exception {
        mockMvc.perform(get("/api/tradeforms/1"))
                .andExpect(status().isOk())
                .andExpect(content().json("{\"id\":1,\"status\":\"PENDING\"}"));
    }

    @Test
    void testGetTradeFormByIdNotFound() throws Exception {
        when(tradeFormService.getTradeFormById(999L))
                .thenThrow(new TradeFormNotFoundException(999L));

        mockMvc.perform(get("/api/tradeforms/999"))
                .andExpect(status().isNotFound())
                .andExpect(content().string("Trade form with ID 999 not found."));
    }
}
