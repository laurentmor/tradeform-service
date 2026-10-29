package com.lm.tradeformservice.repository;

import com.lm.tradeformservice.entity.TradeFormEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data repository for CRUD operations on {@link TradeFormEntity} records.
 *
 * <p>Spring provides the implementation at runtime. For example, call {@code findById(id)} to
 * retrieve an optional entity or {@code save(entity)} to insert or update one. The identifier type
 * is {@link Long}.
 */
@Repository
public interface ITradeFormRepository extends JpaRepository<TradeFormEntity, Long> {}
