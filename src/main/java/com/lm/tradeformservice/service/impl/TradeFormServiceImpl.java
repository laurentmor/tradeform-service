package com.lm.tradeformservice.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import com.lm.tradeformservice.dto.TradeForm;
import com.lm.tradeformservice.dto.TradeFormStatus;
import com.lm.tradeformservice.service.ITradeFormService;
@Service 
@Slf4j 
/**
 * Implementation of the ITradeFormService interface for handling trade form operations.
 * This service provides methods to retrieve the running status of the trade form service
 */
public class TradeFormServiceImpl implements ITradeFormService {
    
    /** Gets the running status of the trade form
     * @return the running status
     */
    @Override
    public String getRunningStatus() {
        log.info("TradeFormServiceImpl.getRunningStatus() called");
        return "TradeForm Service is running";
    }

    /** Gets a trade form by its ID
     * @param id the ID of the trade form
     * @return the trade form
     */
    @Override
    public TradeForm getTradeFormById(final int id) {
            try {
                log.info("getTradeFormById called with id: {}", id);
            return new TradeForm(id, TradeFormStatus.PENDING);
            
            
        } catch (IllegalArgumentException e) {
            log.warn("Invalid TradeForm ID: {}", id);
            throw new IllegalArgumentException("Invalid TradeForm ID");
        } 
    }
}