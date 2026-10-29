package com.lm.tradeformservice.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * HTTP contract for the TradeForm Service endpoints.
 *
 * <p>Routes declared here are implemented by a Spring MVC controller and expose the service status
 * and a trade-form representation.
 */
public interface ITradeFormController {
    /**
     * Retrieves a trade form by its identifier.
     *
     * <p>The current implementation constructs a synthetic {@code PENDING} response rather than
     * loading a persisted entity. Negative identifiers result in HTTP 400.
     *
     * @param id identifier to include in the trade-form response
     * @return HTTP 200 with a {@link com.lm.tradeformservice.dto.TradeForm} body, or HTTP 400 with
     *     an {@link com.lm.tradeformservice.dto.ErrorResponse} body when the identifier is invalid
     */
    @GetMapping("/api/tradeforms/{id}")
    public ResponseEntity<?> getTradeFormById(@PathVariable final long id);

    /**
     * Returns the current running-status message for the service.
     *
     * @return HTTP 200 with the service status as plain text
     */
    @GetMapping("/api")
    public ResponseEntity<String> getRunningStatus();
}
