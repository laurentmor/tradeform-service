package com.lm.tradeformservice.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import com.lm.tradeformservice.dto.TradeForm;

import tools.jackson.databind.json.JsonMapper;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.when;

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

    @MockitoBean
    private JsonMapper jsonMapper;

    @BeforeEach
    public void setUp() throws Exception {
        reset(jsonMapper);
        when(jsonMapper.writeValueAsString(any(TradeForm.class)))
                .thenReturn("{\"id\":1,\"status\":\"PENDING\"}");
    }

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
     * Tests the GET endpoint for retrieving a trade form with a negative ID.
     * @throws Exception if an error occurs during the request.
     */
    @Test
    public void testNegativeId() throws Exception {
        // Perform a GET request to the /api/tradeforms/-1 endpoint and expect a 400 Bad Request status.
        mockMvc.perform(get("/api/tradeforms/-1"))
            .andExpect(status().isBadRequest())
            .andExpect(content().string("Invalid TradeForm ID"));
        }

        @Test
        public void testNonNumericId() throws Exception {
        mockMvc.perform(get("/api/tradeforms/not-a-number"))
            .andExpect(status().isBadRequest())
            .andExpect(content().string("Invalid TradeForm ID"));
        }

        @Test
        public void testSerializationFailure() throws Exception {
        doThrow(new RuntimeException("serialization failed"))
            .when(jsonMapper).writeValueAsString(any(TradeForm.class));

        mockMvc.perform(get("/api/tradeforms/1"))
            .andExpect(status().isInternalServerError())
            .andExpect(content().string("Error converting TradeForm to JSON"));
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
