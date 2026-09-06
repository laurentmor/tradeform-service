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
    @GetMapping("/api/tradeforms/{id}") 
    public ResponseEntity<String> getTradeFormById(@PathVariable String id) {
        TradeForm tradeForm = new TradeForm(Integer.parseInt(id), TradeFormStatus.PENDING);
        //TODO: Move the ObjectMapper to a service class and use it to convert the TradeForm object to JSON

        ObjectMapper jsonMapper = new ObjectMapper();

        return ResponseEntity.ok("Trade Form Service is running!")  ;
    }

}
