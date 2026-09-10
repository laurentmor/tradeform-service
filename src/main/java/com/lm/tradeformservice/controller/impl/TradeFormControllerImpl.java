package com.lm.tradeformservice.controller.impl;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.lm.tradeformservice.controller.ITradeFormController;
import com.lm.tradeformservice.dto.TradeForm;
import com.lm.tradeformservice.dto.TradeFormStatus;

import tools.jackson.databind.json.JsonMapper;

import org.springframework.http.ResponseEntity;

@RestController("basicTradeFormController") 
public class TradeFormControllerImpl implements ITradeFormController {
  
    private final JsonMapper jsonMapper;

    public TradeFormControllerImpl(JsonMapper jsonMapper) {
        this.jsonMapper = jsonMapper;
    }

    @Override
    @GetMapping("/api/tradeforms/{id}") 
    public ResponseEntity<String> getTradeFormById(@PathVariable final String id) {
        try {
            TradeForm tradeForm = new TradeForm(Integer.parseInt(id), TradeFormStatus.PENDING);
            String jsonResponse = jsonMapper.writeValueAsString(tradeForm);
            return ResponseEntity.ok(jsonResponse);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Invalid TradeForm ID");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error converting TradeForm to JSON");
        }
    }

    @Override
    @GetMapping("/api")
    public ResponseEntity<String> getRunningStatus() {
        return ResponseEntity.ok("TradeForm Service is running");
    }
}
