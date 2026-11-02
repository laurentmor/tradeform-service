package com.lm.tradeformservice.entity;

import com.lm.tradeformservice.dto.TradeFormStatus;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * JPA entity representing a trade form stored in the relational database.
 *
 * <p>The database generates the identifier, and the status is persisted by enum name rather than
 * ordinal. New instances default to {@link TradeFormStatus#PENDING}.
 */
// Use the existing database table name instead of the default entity-derived name.
@Table(name = "trade_form")
@Entity
@AllArgsConstructor
@Data // Data annotation generates getters, setters, toString, equals, and hashCode methods
public class TradeFormEntity {
    /** Database-generated unique identifier; {@code null} before persistence. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id = null;

    /** Lifecycle status stored as the enum constant's name. */
    @Enumerated(EnumType.STRING)
    private TradeFormStatus status = TradeFormStatus.PENDING;

    /** Creates an empty entity for JPA instantiation. */
    protected TradeFormEntity() {}
}
