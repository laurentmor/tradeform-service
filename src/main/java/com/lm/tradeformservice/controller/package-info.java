/**
 * HTTP API contracts for TradeForm Service.
 *
 * <p>{@link com.lm.tradeformservice.controller.ITradeFormController} declares the routes exposed by
 * the application. The implementation is in {@code controller.impl}; this package contains the
 * controller-facing API contract.
 *
 * <h2>Available routes</h2>
 *
 * <ul>
 *   <li>{@code GET /api} returns the service status as plain text.
 *   <li>{@code GET /api/tradeforms/{id}} returns a trade-form DTO for a non-negative ID.
 * </ul>
 *
 * <h2>Request example</h2>
 *
 * <pre>{@code
 * curl http://localhost:8080/api
 * curl http://localhost:8080/api/tradeforms/42
 * }</pre>
 *
 * <p>A successful trade-form request currently returns JSON such as:
 *
 * <pre>{@code
 * {
 *   "id": 42,
 *   "status": "PENDING"
 * }
 * }</pre>
 *
 * <p>A negative ID is rejected with HTTP 400 and an {@code ErrorResponse}. The trade-form route
 * currently returns a synthetic pending form rather than loading it from the database.
 */
package com.lm.tradeformservice.controller;
