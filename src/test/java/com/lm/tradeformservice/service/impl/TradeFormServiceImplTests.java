package com.lm.tradeformservice.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.lm.tradeformservice.config.MapperConfig;
import com.lm.tradeformservice.dto.TradeForm;
import com.lm.tradeformservice.dto.TradeFormStatus;
import com.lm.tradeformservice.entity.TradeFormEntity;
import com.lm.tradeformservice.repository.ITradeFormRepository;
import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

/** Unit tests for the service implementation's repository and mapping behavior. */
@ExtendWith(MockitoExtension.class)
public class TradeFormServiceImplTests {

    /** Mocked repository used to isolate service behavior. */
    @Mock private ITradeFormRepository tradeFormRepository;

    /** Service under test. */
    private TradeFormServiceImpl tradeFormService;

    /** Creates the service with a real mapper and mocked persistence boundary. */
    @BeforeEach
    void setUp() {
        ModelMapper modelMapper = new MapperConfig().modelMapper();
        tradeFormService = new TradeFormServiceImpl(tradeFormRepository, modelMapper);
    }

    /** Verifies that lookup reports an absent trade form. */
    @Test
    void getTradeFormByIdThrowsWhenTradeFormDoesNotExist() {
        when(tradeFormRepository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> tradeFormService.getTradeFormById(99L));
    }

    /** Verifies that invalid identifiers are rejected before repository access. */
    @Test
    void getTradeFormByIdDoesNotQueryRepositoryForNegativeId() {
        assertThrows(IllegalArgumentException.class, () -> tradeFormService.getTradeFormById(-1L));

        verify(tradeFormRepository, never()).findById(any());
    }

    /** Verifies that all persisted entities are mapped to DTOs. */
    @Test
    void getAllTradeFormsMapsEveryEntity() {
        when(tradeFormRepository.findAll())
                .thenReturn(
                        List.of(
                                new TradeFormEntity(1L, TradeFormStatus.PENDING),
                                new TradeFormEntity(2L, TradeFormStatus.APPROVED)));

        List<TradeForm> tradeForms = tradeFormService.getAllTradeForms();

        assertEquals(
                List.of(
                        new TradeForm(1L, TradeFormStatus.PENDING),
                        new TradeForm(2L, TradeFormStatus.APPROVED)),
                tradeForms);
    }

    /** Verifies that an empty repository result becomes an empty DTO list. */
    @Test
    void getAllTradeFormsReturnsEmptyListWhenRepositoryIsEmpty() {
        when(tradeFormRepository.findAll()).thenReturn(List.of());

        assertEquals(List.of(), tradeFormService.getAllTradeForms());
    }

    /** Verifies that creation maps, persists, and maps the saved entity. */
    @Test
    void createTradeFormMapsSavesAndMapsResult() {
        TradeForm requestedTradeForm = new TradeForm(0L, TradeFormStatus.REJECTED);
        TradeFormEntity savedEntity = new TradeFormEntity(42L, TradeFormStatus.REJECTED);
        when(tradeFormRepository.save(any(TradeFormEntity.class))).thenReturn(savedEntity);

        TradeForm createdTradeForm = tradeFormService.createTradeForm(requestedTradeForm);

        assertEquals(new TradeForm(42L, TradeFormStatus.REJECTED), createdTradeForm);
        verify(tradeFormRepository).save(new TradeFormEntity(0L, TradeFormStatus.REJECTED));
    }
}
