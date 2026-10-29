/**
 * Spring MVC implementations of the TradeForm Service HTTP contracts.
 *
 * <p>{@link com.lm.tradeformservice.controller.impl.TradeFormControllerImpl} is registered as a
 * REST controller and delegates requests to {@link
 * com.lm.tradeformservice.service.ITradeFormService}. It returns HTTP 200 for successful service
 * calls and converts an {@link java.lang.IllegalArgumentException} from an invalid ID into HTTP 400
 * with an {@link com.lm.tradeformservice.dto.ErrorResponse} body.
 *
 * <h2>Calling the controller over HTTP</h2>
 *
 * <pre>{@code
 * curl -i http://localhost:8080/api/tradeforms/7
 * curl -i http://localhost:8080/api/tradeforms/-1
 * }</pre>
 *
 * <p>The first request returns the service result. The second returns a bad-request response
 * because trade-form IDs cannot be negative.
 */
package com.lm.tradeformservice.controller.impl;
