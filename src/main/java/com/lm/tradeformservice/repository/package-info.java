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
 * <p>The service layer uses this repository for trade-form lookup, listing, and persistence.
 * Operations require a configured and reachable relational database.
 */
package com.lm.tradeformservice.repository;
