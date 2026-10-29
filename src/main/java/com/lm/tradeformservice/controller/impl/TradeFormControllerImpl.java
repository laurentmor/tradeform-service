package com.lm.tradeformservice.controller.impl;

import com.lm.tradeformservice.controller.ITradeFormController;
import com.lm.tradeformservice.dto.ErrorResponse;
import com.lm.tradeformservice.dto.TradeForm;
import com.lm.tradeformservice.service.ITradeFormService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * Spring MVC adapter for the TradeForm Service HTTP API.
 *
 * <p>Delegates endpoint behavior to {@link ITradeFormService} and translates invalid trade-form
 * identifiers into a client error response.
 */
@RestController("basicTradeFormController")
@Slf4j
public class TradeFormControllerImpl implements ITradeFormController {

    /** Business service that supplies the result for each API request. */
    private ITradeFormService tradeFormService;

    /**
     * Creates the controller with its business-service dependency.
     *
     * @param tradeFormService service used to perform the requested operation
     */
    public TradeFormControllerImpl(final ITradeFormService tradeFormService) {
        this.tradeFormService = tradeFormService;
        log.info("TradeFormControllerImpl initialized");
    }

    @Override
    @GetMapping("/api/tradeforms/{id}")
    /**
     * Returns the trade-form representation for the requested identifier.
     *
     * @param id identifier supplied in the request path
     * @return HTTP 200 with the service result, or HTTP 400 with an error body if the identifier is
     *     invalid
     */
    public ResponseEntity<?> getTradeFormById(@PathVariable final long id) {

        try {
            TradeForm tradeForm = tradeFormService.getTradeFormById(id);
            log.info("TradeForm retrieved successfully for id: {}", id);
            return ResponseEntity.ok(tradeForm);
        } catch (IllegalArgumentException e) {
            log.warn("Invalid TradeForm ID provided: {}", id);
            return ResponseEntity.badRequest().body(new ErrorResponse("Invalid TradeForm ID"));
        }
    }

    @Override
    @GetMapping("/api")
    /**
     * Returns the service's running-status message.
     *
     * @return HTTP 200 with the status message as plain text
     */
    public ResponseEntity<String> getRunningStatus() {
        String status = tradeFormService.getRunningStatus();
        log.info("TradeFormControllerImpl.getRunningStatus() called, status: {}", status);
        return ResponseEntity.ok(status);
    }
}
