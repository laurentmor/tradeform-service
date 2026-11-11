/**
 * Service contracts that define TradeForm Service business operations.
 *
 * <p>{@link com.lm.tradeformservice.service.ITradeFormService} exposes operations for retrieving
 * the service status, looking up and listing persisted trade forms, and creating trade forms. Its
 * Spring-managed implementation is in {@code com.lm.tradeformservice.service.impl}.
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
 * <p>Looking up a trade form with a negative identifier results in {@link
 * com.lm.tradeformservice.exception.data.NegativeTradeFormIdException}; looking up an identifier
 * that is not present results in {@link
 * com.lm.tradeformservice.exception.data.TradeFormNotFoundException}.
 */
package com.lm.tradeformservice.service;
