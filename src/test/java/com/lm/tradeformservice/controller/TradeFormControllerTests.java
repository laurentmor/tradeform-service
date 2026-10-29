package com.lm.tradeformservice.controller;

import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.lm.tradeformservice.TradeFormServiceApplication;
import com.lm.tradeformservice.controller.impl.TradeFormControllerImpl;
import com.lm.tradeformservice.dto.TradeForm;
import com.lm.tradeformservice.dto.TradeFormStatus;
import com.lm.tradeformservice.service.ITradeFormService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

/**
 * MVC tests for {@link TradeFormControllerImpl}.
 *
 * <p>Uses a mocked {@link ITradeFormService} and standalone MockMvc to verify the status endpoint,
 * successful trade-form JSON response, and HTTP 400 response for invalid identifiers.
 */
@SpringBootTest(classes = TradeFormServiceApplication.class)
public class TradeFormControllerTests {

    /** Mock MVC dispatcher configured for the controller under test. */
    private MockMvc mockMvc;

    /** Mock service used to isolate HTTP behavior from business logic. */
    @MockitoBean private ITradeFormService tradeFormService;

    /**
     * Resets the service mock, stubs expected behavior, and builds the standalone MVC fixture.
     *
     * @throws Exception if MockMvc setup fails
     */
    @BeforeEach
    public void setUp() throws Exception {
        reset(tradeFormService);
        when(tradeFormService.getRunningStatus()).thenReturn("TradeForm Service is running");
        when(tradeFormService.getTradeFormById(1L))
                .thenReturn(new TradeForm(1L, TradeFormStatus.PENDING));
        mockMvc =
                MockMvcBuilders.standaloneSetup(new TradeFormControllerImpl(tradeFormService))
                        .build();
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
     * Verifies that a negative identifier is translated to HTTP 400 with an error JSON body.
     *
     * @throws Exception if the MockMvc request fails
     */
    @Test
    void testNegativeId() throws Exception {
        when(tradeFormService.getTradeFormById(-1))
                .thenThrow(new IllegalArgumentException("Invalid TradeForm ID"));

        mockMvc.perform(get("/api/tradeforms/-1"))
                .andExpect(status().isBadRequest())
                .andExpect(content().json("{\"message\":\"Invalid TradeForm ID\"}"));
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
}
