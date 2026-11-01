package com.lm.tradeformservice.service.impl;

import com.lm.tradeformservice.dto.TradeForm;
import com.lm.tradeformservice.entity.TradeFormEntity;
import com.lm.tradeformservice.repository.ITradeFormRepository;
import com.lm.tradeformservice.service.ITradeFormService;
import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

/**
 * Default Spring service implementation for trade-form operations.
 *
 * <p>The status endpoint returns a fixed message. Trade-form operations use the repository and
 * mapper to translate between persistence entities and API DTOs.
 */
@Service
@Slf4j
public class TradeFormServiceImpl implements ITradeFormService {

    /** Repository available for trade-form persistence operations. */
    private final ITradeFormRepository tradeFormRepository;

    /** Mapper used to convert between persistence entities and API DTOs. */
    private final ModelMapper tradeFormMapper;

    /**
     * Creates the service with its persistence and mapping dependencies.
     *
     * @param tradeFormRepository repository used to persist trade forms
     * @param tradeFormMapper mapper used to convert entities and DTOs
     */
    public TradeFormServiceImpl(
            final ITradeFormRepository tradeFormRepository, final ModelMapper tradeFormMapper) {
        this.tradeFormRepository = tradeFormRepository;
        this.tradeFormMapper = tradeFormMapper;
    }

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
            TradeFormEntity entity =
                    tradeFormRepository
                            .findById(id)
                            .orElseThrow(
                                    () ->
                                            new EntityNotFoundException(
                                                    "TradeForm not found with ID: " + id));
            TradeForm tradeForm = tradeFormMapper.map(entity, TradeForm.class);
            return tradeForm;

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
        List<TradeFormEntity> entities = tradeFormRepository.findAll();
        return entities.stream()
                .map(entity -> tradeFormMapper.map(entity, TradeForm.class))
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
        TradeFormEntity entity = tradeFormMapper.map(tradeForm, TradeFormEntity.class);
        entity = tradeFormRepository.save(entity);
        return tradeFormMapper.map(entity, TradeForm.class);
    }
}
