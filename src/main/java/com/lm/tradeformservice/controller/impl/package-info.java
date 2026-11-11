/**
 * Spring MVC implementations of the TradeForm Service HTTP contracts.
 *
 * <p>{@link com.lm.tradeformservice.controller.impl.TradeFormControllerImpl} is registered as a
 * REST controller and delegates requests to {@link
 * com.lm.tradeformservice.service.ITradeFormService}. It returns HTTP 200 for successful service
 * calls. The global exception handler maps an {@link
 * com.lm.tradeformservice.exception.data.NegativeTradeFormIdException} to HTTP 400 and a {@link
 * com.lm.tradeformservice.exception.data.TradeFormNotFoundException} to HTTP 404; both error
 * responses contain the exception message as plain text.
 *
 * <h2>Calling the controller over HTTP</h2>
 *
 * <pre>{@code
 * curl -i http://localhost:8080/api/tradeforms/7
 * curl -i http://localhost:8080/api/tradeforms/-1
 * }</pre>
 *
 * <p>The first request returns the matching persisted trade form. The second returns a bad-request
 * response because trade-form IDs cannot be negative.
 */
package com.lm.tradeformservice.controller.impl;
