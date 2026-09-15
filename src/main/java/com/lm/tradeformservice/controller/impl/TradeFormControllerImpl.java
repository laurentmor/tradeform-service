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
  
  /**
   *  DefaulT constructor for TradeFormControllerImpl
   *  @param tradeFormService the trade form service to be used
   *  */  
  public TradeFormControllerImpl( final ITradeFormService tradeFormService) {
        this.tradeFormService = tradeFormService;
        log.info("TradeFormControllerImpl initialized");
    }

    @Override
    @GetMapping("/api/tradeforms/{id}")
    /**
     * Retrieves a TradeForm by its ID.
     *
     * @param id the ID of the TradeForm to retrieve
     * @return a ResponseEntity containing the TradeForm if found, or an appropriate error response
     */ 
    public ResponseEntity<?> getTradeFormById(@PathVariable final int id) {

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
    /**
     * Checks the running status of the TradeForm Service.
     *
     * @return a ResponseEntity containing the status message
     */
    public ResponseEntity<String> getRunningStatus() {
        String status = tradeFormService.getRunningStatus();
        log.info("TradeFormControllerImpl.getRunningStatus() called, status: {}", status);
        return ResponseEntity.ok(status);
    }
}       
