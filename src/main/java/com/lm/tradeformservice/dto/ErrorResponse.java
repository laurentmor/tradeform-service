package com.lm.tradeformservice.dto;

/**
 * Simple API response body containing a client-facing error message.
 *
 * @param message explanation of the error returned to the client
 */
public record ErrorResponse(String message) {}
