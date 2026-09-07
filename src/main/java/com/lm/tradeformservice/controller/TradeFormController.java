package com.lm.tradeformservice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.lm.tradeformservice.dto.TradeForm;
import com.lm.tradeformservice.dto.TradeFormStatus;

import tools.jackson.databind.ObjectMapper;

import org.springframework.http.ResponseEntity;

@RestController 
public class TradeFormController {
    /**
     * GET endpoint to check if the TradeForm Service is running
     * @return ResponseEntity with a message indicating the service is running
     */
    @GetMapping("/api")
    public ResponseEntity<String> getRunningStatus() {

        return ResponseEntity.ok("TradeForm Service is running");
    }
    /**
     * GET endpoint to retrieve a TradeForm by its ID
     * @param id The ID of the TradeForm to retrieve
     * @return ResponseEntity containing the TradeForm in JSON format or an error message
     */
    @GetMapping("/api/tradeforms/{id}") 
    public ResponseEntity<String> getTradeFormById(@PathVariable String id) {
        TradeForm tradeForm = new TradeForm(Integer.parseInt(id), TradeFormStatus.PENDING);
        //TODO: Move the ObjectMapper to a service class and use it to convert the TradeForm object to JSON

        ObjectMapper jsonMapper = new ObjectMapper();
        try {
            String jsonResponse = jsonMapper.writeValueAsString(tradeForm);
            return ResponseEntity.ok(jsonResponse);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error converting TradeForm to JSON");
        }

       
    }

}
