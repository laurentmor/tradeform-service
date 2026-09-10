package com.lm.tradeformservice.controller;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * Controller interface for managing TradeForm resources.
 * 
 */
public interface ITradeFormController {
    /**
     * Retrieves a TradeForm by its ID.
     *
     * @param id the ID of the TradeForm to retrieve
     * @return a ResponseEntity containing the TradeForm if found, or an appropriate error response
     */
    @GetMapping("/api/tradeforms/{id}") 
    public ResponseEntity<String>  getTradeFormById(@PathVariable final  String id); 
    /**
     * Checks the running status of the TradeForm Service.
     *
     * @return a ResponseEntity containing the status message
     */
    
    @GetMapping("/api")
    public ResponseEntity<String> getRunningStatus();    
}