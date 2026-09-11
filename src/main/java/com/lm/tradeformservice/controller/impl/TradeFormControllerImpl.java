package com.lm.tradeformservice.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import tools.jackson.core.JacksonException;
import com.lm.tradeformservice.controller.ITradeFormController;
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
    public ResponseEntity<String> getTradeFormById(@PathVariable final String id) {

        try {
            String jsonResponse = tradeFormService.getTradeFormById(id);
            return ResponseEntity.ok(jsonResponse);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Invalid TradeForm ID");
        } catch (JacksonException e) {
            return ResponseEntity.status(500).body("Error converting TradeForm to JSON");
        }
    
    }

    @Override
    @GetMapping("/api")
    public ResponseEntity<String> getRunningStatus() {
        return ResponseEntity.ok(tradeFormService.getRunningStatus());
    }
}
