package com.lm.tradeformservice.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(TradeFormController.class)
public class TradeFormControllerTests {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void healthCheck() throws Exception {
        mockMvc.perform(get("/api"))
                .andExpect(status().isOk())
            .andExpect(content().string("TradeForm Service is running"));
    }
}
