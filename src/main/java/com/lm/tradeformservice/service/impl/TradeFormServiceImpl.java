package com.lm.tradeformservice.service.impl;


import org.springframework.stereotype.Service;

import com.lm.tradeformservice.dto.TradeForm;
import com.lm.tradeformservice.dto.TradeFormStatus;
import com.lm.tradeformservice.service.ITradeFormService;

import tools.jackson.core.JacksonException;
import tools.jackson.databind.json.JsonMapper;
@Service 
public class TradeFormServiceImpl implements ITradeFormService {
    private  JsonMapper jsonMapper;
    public TradeFormServiceImpl(final JsonMapper jsonMapper) {
        this.jsonMapper = jsonMapper;
    }

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
    public String getTradeFormById(String id) throws IllegalArgumentException, JacksonException {
            try {
            TradeForm tradeForm = new TradeForm(Integer.parseInt(id), TradeFormStatus.PENDING);
            String jsonResponse = jsonMapper.writeValueAsString(tradeForm);
            return jsonResponse;
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid TradeForm ID");
        } catch (JacksonException e) {
            throw e;
        }
    }
}