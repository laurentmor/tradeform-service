/**
 * Spring Data repositories for storing and retrieving persistence entities.
 *
 * <p>{@link com.lm.tradeformservice.repository.ITradeFormRepository} extends {@code
 * JpaRepository<TradeFormEntity, Long>}, so Spring provides common create, read, update, and delete
 * operations without requiring a handwritten repository implementation.
 *
 * <h2>Repository usage</h2>
 *
 * <p>Inject the repository into a Spring-managed service and use its CRUD methods:
 *
 * <pre>{@code
 * Optional<TradeFormEntity> existing = tradeFormRepository.findById(42L);
 *
 * TradeFormEntity draft = new TradeFormEntity(null, TradeFormStatus.PENDING);
 * TradeFormEntity saved = tradeFormRepository.save(draft);
 *
 * tradeFormRepository.deleteById(saved.getId());
 * }</pre>
 *
 * <p>These operations require a configured and reachable relational database. The current REST
 * service does not yet use this repository to serve its trade-form GET endpoint.
 */
package com.lm.tradeformservice.repository;
