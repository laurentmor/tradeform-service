package com.lm.tradeformservice.repository;

import com.lm.tradeformservice.entity.TradeFormEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ITradeFormRepository extends JpaRepository<TradeFormEntity, Long> {}
