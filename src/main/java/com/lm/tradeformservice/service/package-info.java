/**
 * Service contracts that define TradeForm Service business operations.
 *
 * <p>{@link com.lm.tradeformservice.service.ITradeFormService} exposes operations for retrieving
 * the service status and requesting a trade form by ID. Its Spring-managed implementation is in
 * {@code com.lm.tradeformservice.service.impl}.
 *
 * <h2>Using the service contract</h2>
 *
 * <p>Inject the interface into a Spring-managed component rather than depending directly on its
 * implementation:
 *
 * <pre>{@code
 * @Service
 * class TradeFormSummary {
 *     private final ITradeFormService tradeFormService;
 *
 *     TradeFormSummary(ITradeFormService tradeFormService) {
 *         this.tradeFormService = tradeFormService;
 *     }
 *
 *     TradeForm load(long id) {
 *         return tradeFormService.getTradeFormById(id);
 *     }
 * }
 * }</pre>
 *
 * <p>IDs must be non-negative. An invalid ID is reported as {@link
 * java.lang.IllegalArgumentException}. The current implementation returns a synthetic pending trade
 * form; database-backed lookup is not yet connected to this contract.
 */
package com.lm.tradeformservice.service;
