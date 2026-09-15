package com.lm.tradeformservice.service;

import com.lm.tradeformservice.dto.TradeForm;

/** 
 * Service interface for handling trade form operations
 * 
 */
public interface ITradeFormService {
    /** Gets the running status of the trade form
     * @return the running status
     */
    public String getRunningStatus();  
    /** Gets a trade form by its ID
     * @param id the ID of the trade form
     * @return the trade form
     * @throws IllegalArgumentException if the ID is invalid
     * 
     */
    public TradeForm getTradeFormById(final   String id)throws IllegalArgumentException    ;

}
