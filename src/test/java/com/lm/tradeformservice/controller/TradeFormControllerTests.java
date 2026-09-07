package com.lm.tradeformservice.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
/**
 * Tests for {@link TradeFormController}.
 */
@WebMvcTest(TradeFormController.class)
public class TradeFormControllerTests {
    /**
     * MockMvc instance for testing the controller.
     */
    @Autowired
    private MockMvc mockMvc;

    /**
     * Tests the health check endpoint of the TradeFormController.
     * @throws Exception if an error occurs during the request.
     */
    @Test
    public void healthCheck() throws Exception {
        // Perform a GET request to the /api endpoint and expect a 200 OK status with the response "TradeForm Service is running".
        mockMvc.perform(get("/api"))
                .andExpect(status().isOk())
            .andExpect(content().string("TradeForm Service is running"));
    }
    
    /**
     * Tests the GET endpoint for retrieving a trade form by its ID.
     * @throws Exception if an error occurs during the request.
     */
    @Test
    public void testGetTradeFormById() throws Exception {
        // Perform a GET request to the /api/tradeforms/1 endpoint and expect a 200 OK status with the correct JSON response.
        mockMvc.perform(get("/api/tradeforms/1"))
                .andExpect(status().isOk())
                .andExpect(content().json("{\"id\":1,\"status\":\"PENDING\"}"));
    }
}
