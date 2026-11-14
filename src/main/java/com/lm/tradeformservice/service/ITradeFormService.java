package com.lm.tradeformservice.service;

import com.lm.tradeformservice.dto.TradeForm;
import java.util.List;

/**
 * Business-operation contract for TradeForm Service.
 *
 * <p>Implementations provide the service status and operations for looking up, listing, and
 * creating persisted trade forms.
 */
public interface ITradeFormService {
    /**
     * Gets the service's running-status message.
     *
     * @return human-readable status text
     */
    public String getRunningStatus();

    /**
     * Gets a trade form by its identifier.
     *
     * <p>Implementations retrieve the form from persistence and map it to the API DTO.
     *
     * @param id non-negative trade-form identifier
     * @return trade-form response containing the requested identifier and current status
     * @throws com.lm.tradeformservice.exception.data.NegativeTradeFormIdException if {@code id} is
     *     negative
     * @throws com.lm.tradeformservice.exception.data.TradeFormNotFoundException if no form has the
     *     requested identifier
     */
    public TradeForm getTradeFormById(final long id);

    /**
     * Gets all persisted trade forms.
     *
     * @return all persisted trade forms, or an empty list when none exist
     */
    public List<TradeForm> getAllTradeForms();

    /**
     * Persists a new trade form.
     *
     * @param tradeForm trade-form data to persist
     * @return the saved trade form, including its persistence-generated identifier
     */
    public TradeForm createTradeForm(final TradeForm tradeForm);
}
