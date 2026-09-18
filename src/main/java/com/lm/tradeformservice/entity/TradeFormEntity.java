package com.lm.tradeformservice.entity;

import static lombok.AccessLevel.PROTECTED;

import com.lm.tradeformservice.dto.TradeFormStatus;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor(access = PROTECTED)
/**
 * TradeFormEntity class represents the TradeForm entity in the database. It contains the ID and
 * status of the trade form.
 */
public class TradeFormEntity {
    /** The unique identifier for the trade form entity. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private Long id;

    /** The status of the trade form entity, represented by the TradeFormStatus enum. */
    @Getter
    @Setter
    @Enumerated(EnumType.STRING)
    private TradeFormStatus status = TradeFormStatus.PENDING;
}
