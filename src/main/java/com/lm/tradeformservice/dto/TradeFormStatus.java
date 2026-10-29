package com.lm.tradeformservice.dto;

/**
 * Lifecycle states currently supported for a trade form.
 *
 * <p>The enum name is used in API JSON and is persisted by JPA as a string. Use {@link
 * #getDisplayName()} when a human-readable label is required.
 */
public enum TradeFormStatus {
    /** The trade form is awaiting a decision. */
    PENDING("Pending"),

    /** The trade form has been approved. */
    APPROVED("Approved"),
    /** The trade form is awaiting approval. */
    WAITING_FOR_APPROVAL("Waiting for Approval"),
    /** The trade form has been rejected. */
    REJECTED("Rejected");

    /** Human-readable label associated with this status. */
    private final String displayName;

    /**
     * Associates an enum constant with its user-facing label.
     *
     * @param displayName display label for the status
     */
    TradeFormStatus(String displayName) {
        this.displayName = displayName;
    }

    /**
     * Returns the human-readable label for this status.
     *
     * @return the display label
     */
    public String getDisplayName() {
        return displayName;
    }
}
