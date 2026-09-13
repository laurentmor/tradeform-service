package com.lm.tradeformservice.controller.impl;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import com.lm.tradeformservice.controller.ITradeFormController;
import com.lm.tradeformservice.dto.ErrorResponse;
import com.lm.tradeformservice.dto.TradeForm;
import com.lm.tradeformservice.service.ITradeFormService;

import lombok.extern.slf4j.Slf4j;

@RestController("basicTradeFormController") 
@Slf4j 
public class TradeFormControllerImpl implements ITradeFormController {
  
  private ITradeFormService tradeFormService;
  
    public TradeFormControllerImpl( final ITradeFormService tradeFormService) {
        this.tradeFormService = tradeFormService;
        String controlerStateString = (tradeFormService != null) ? "initialized with TradeFormService" : "initialized without TradeFormService";
        log.info("TradeFormControllerImpl constructor called, controller state: {}", controlerStateString); 
    }

    @Override
    @GetMapping("/api/tradeforms/{id}") 
    public ResponseEntity<?> getTradeFormById(@PathVariable final String id) {

        try {
            TradeForm tradeForm = tradeFormService.getTradeFormById(id);
            log.info("TradeForm retrieved successfully for id: {}", id);
            return ResponseEntity.ok(tradeForm);
        } catch (IllegalArgumentException e) {
            log.warn("Invalid TradeForm ID provided: {}", id);
            return ResponseEntity
            .badRequest()
            .body(new ErrorResponse("Invalid TradeForm ID"));
        } 
    
    }

    @Override
    @GetMapping("/api")

    public ResponseEntity<String> getRunningStatus() {
        log.info("TradeFormControllerImpl.getRunningStatus() called {}",tradeFormService.getRunningStatus())   ;

        return ResponseEntity.ok(tradeFormService.getRunningStatus());
    }
}       
