package com.lm.tradeformservice.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import com.lm.tradeformservice.controller.ITradeFormController;
import com.lm.tradeformservice.dto.ErrorResponse;
import com.lm.tradeformservice.dto.TradeForm;
import com.lm.tradeformservice.service.ITradeFormService;

@RestController("basicTradeFormController") 
public class TradeFormControllerImpl implements ITradeFormController {
  @Autowired 
  private ITradeFormService tradeFormService;
  
    public TradeFormControllerImpl( final ITradeFormService tradeFormService) {
        this.tradeFormService = tradeFormService;
    }

    @Override
    @GetMapping("/api/tradeforms/{id}") 
    public ResponseEntity<?> getTradeFormById(@PathVariable final String id) {

        try {
            TradeForm tradeForm = tradeFormService.getTradeFormById(id);
            return ResponseEntity.ok(tradeForm);
        } catch (IllegalArgumentException e) {
            return ResponseEntity
            .badRequest()
            .body(new ErrorResponse("Invalid TradeForm ID"));
        } 
    
    }

    @Override
    @GetMapping("/api")
    public ResponseEntity<String> getRunningStatus() {
        return ResponseEntity.ok(tradeFormService.getRunningStatus());
    }
}
