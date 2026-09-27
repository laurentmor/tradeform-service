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
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor(access = PROTECTED)
@Data // Data annotation generates getters, setters, toString, equals, and hashCode methods
/**
 * TradeFormEntity class represents the TradeForm entity in the database. It contains the ID and
 * status of the trade form.
 */
public class TradeFormEntity {
    /** The unique identifier for the trade form entity. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id = null;

    /** The status of the trade form entity, represented by the TradeFormStatus enum. */
    @Enumerated(EnumType.STRING)
    private TradeFormStatus status = TradeFormStatus.PENDING;
}
