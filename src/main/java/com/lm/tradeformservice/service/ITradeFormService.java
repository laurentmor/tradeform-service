package com.lm.tradeformservice.service;

import com.lm.tradeformservice.dto.TradeForm;
import java.util.List;

/**
 * Business-operation contract for TradeForm Service.
 *
 * <p>Implementations are responsible for the status and trade-form lookup operations used by the
 * HTTP layer.
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
     * <p>The current implementation returns a synhetic trade form with {@code PENDING} status and
     * does not query the repository.
     *
     * @param id non-negative trade-form identifier
     * @return trade-form response containing the requested identifier and current status
     * @throws IllegalArgumentException if {@code id} is negative
     */
    public TradeForm getTradeFormById(final long id);

    /**
     * Gets all trade forms.
     *
     * @return a list of all trade forms
     */
    public List<TradeForm> getAllTradeForms();

    /**
     * Creates a new trade form.
     *
     * @param tradeForm
     * @return
     */
    public TradeForm createTradeForm(final TradeForm tradeForm);
}
