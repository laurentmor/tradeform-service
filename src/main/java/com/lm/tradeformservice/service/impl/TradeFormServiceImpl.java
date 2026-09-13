package com.lm.tradeformservice.service.impl;


import org.springframework.stereotype.Service;

import com.lm.tradeformservice.dto.TradeForm;
import com.lm.tradeformservice.dto.TradeFormStatus;
import com.lm.tradeformservice.service.ITradeFormService;
@Service 
public class TradeFormServiceImpl implements ITradeFormService {
    
    /** Gets the running status of the trade form
     * @return the running status
     */
    @Override
    public String getRunningStatus() {
        return "TradeForm Service is running";
    }

    /** Gets a trade form by its ID
     * @param id the ID of the trade form
     * @return the trade form
     */
    @Override
    public TradeForm getTradeFormById(String id) throws IllegalArgumentException{
            try {
            return new TradeForm(Integer.parseInt(id), TradeFormStatus.PENDING);
            
            
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid TradeForm ID");
        } 
    }
}