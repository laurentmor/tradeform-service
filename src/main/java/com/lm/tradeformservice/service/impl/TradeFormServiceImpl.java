package com.lm.tradeformservice.service.impl;

import com.lm.tradeformservice.dto.TradeForm;
import com.lm.tradeformservice.entity.TradeFormEntity;
import com.lm.tradeformservice.exception.data.NegativeTradeFormIdException;
import com.lm.tradeformservice.exception.data.TradeFormNotFoundException;
import com.lm.tradeformservice.repository.ITradeFormRepository;
import com.lm.tradeformservice.service.ITradeFormService;
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
     * <p>The identifier is validated by the service, and the repository is queried for the
     * corresponding persisted entity.
     *
     * @param id non-negative trade-form identifier
     * @return the persisted trade form
     * @throws NegativeTradeFormIdException if {@code id} is negative
     * @throws TradeFormNotFoundException if no form has the requested identifier
     */
    @Override
    public TradeForm getTradeFormById(final long id) {
        // Validate the identifier before querying the repository
        if (id < 0) {

            throw new NegativeTradeFormIdException(id);
        }

        log.info("Fetching trade form with ID: {}", id);
        TradeFormEntity entity =
                tradeFormRepository
                        .findById(id)
                        .orElseThrow(() -> new TradeFormNotFoundException(id));
        TradeForm tradeForm = tradeFormMapper.map(entity, TradeForm.class);
        return tradeForm;
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
