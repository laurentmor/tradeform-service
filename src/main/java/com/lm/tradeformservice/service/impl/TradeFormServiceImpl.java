package com.lm.tradeformservice.service.impl;

import com.lm.tradeformservice.dto.TradeForm;
import com.lm.tradeformservice.entity.TradeFormEntity;
import com.lm.tradeformservice.repository.ITradeFormRepository;
import com.lm.tradeformservice.service.ITradeFormService;
import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * Default Spring service implementation for trade-form operations.
 *
 * <p>The status endpoint returns a fixed message. Trade-form operations use the repository and
 * mapper to translate between persistence entities and API DTOs.
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class TradeFormServiceImpl implements ITradeFormService {

    /** Repository available for trade-form persistence operations. */
    private final ITradeFormRepository tradeFormRepository;

    /**
     * Returns the fixed running-status message.
     *
     * @return {@code "TradeForm Service is running"}
     */
    @Override
    public String getRunningStatus() {

        log.info("TradeFormServiceImpl.getRunningStatus() called");
        return "TradeForm Service is running";
    }

    /**
     * Retrieves a persisted trade form for the given identifier.
     *
     * <p>The DTO constructor validates that the identifier is non-negative.
     *
     * @param id non-negative trade-form identifier
     * @return the persisted trade form
     * @throws IllegalArgumentException if {@code id} is negative
     */
    @Override
    public TradeForm getTradeFormById(final long id) {
        try {
            if (id < 0) {
                throw new IllegalArgumentException("TradeForm ID must be non-negative");
            }

            log.info("Fetching trade form with ID: {}", id);
            return tradeFormRepository
                    .findById(id)
                    .map(entity -> new TradeForm(entity.getId(), entity.getStatus()))
                    .orElseThrow(
                            () ->
                                    new EntityNotFoundException(
                                            "TradeForm not found with ID: " + id));

        } catch (IllegalArgumentException e) {
            log.warn("Invalid TradeForm ID: {}", id);
            throw new IllegalArgumentException("Invalid TradeForm ID");
        }
    }

    /**
     * (non-Javadoc)
     *
     * @see com.lm.tradeformservice.service.ITradeFormService#getAllTradeForms()
     */
    @Override
    public List<TradeForm> getAllTradeForms() {
        log.info("TradeFormServiceImpl.getAllTradeForms() called");
        return tradeFormRepository.findAll().stream()
                .map(entity -> new TradeForm(entity.getId(), entity.getStatus()))
                .toList();
    }

    /**
     * (non-Javadoc)
     *
     * @see
     *     com.lm.tradeformservice.service.ITradeFormService#createTradeForm(com.lm.tradeformservice.dto.TradeForm)
     */
    @Override
    public TradeForm createTradeForm(final TradeForm tradeForm) {
        log.info("TradeFormServiceImpl.createTradeForm() called with tradeForm: {}", tradeForm);
        var entity = new TradeFormEntity(null, tradeForm.status());
        var savedEntity = tradeFormRepository.save(entity);
        return new TradeForm(savedEntity.getId(), savedEntity.getStatus());
    }
}
