package com.lm.tradeformservice.service;

import com.lm.tradeformservice.dto.TradeForm;
import com.lm.tradeformservice.dto.TradeFormStatus;
import com.lm.tradeformservice.entity.TradeFormEntity;
import com.lm.tradeformservice.repository.ITradeFormRepository;
import com.lm.tradeformservice.service.impl.TradeFormServiceImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/** Integration tests for the Spring-managed {@link TradeFormServiceImpl}. */
@SpringBootTest
public class TradeFormServiceTests {

    /** Service implementation wired by the application context. */
    @Autowired private TradeFormServiceImpl tradeFormService;

    /** Repository used to create the persisted form retrieved by the service test. */
    @Autowired private ITradeFormRepository tradeFormRepository;

    /** Entity created for the retrieval test. */
    private TradeFormEntity testForm;

    /** Creates the persisted form used by the retrieval test. */
    @BeforeEach
    public void setup() {
        testForm = tradeFormRepository.save(new TradeFormEntity(null, TradeFormStatus.PENDING));
    }

    /** Removes the persisted test fixture after each test. */
    @AfterEach
    public void cleanup() {
        tradeFormRepository.delete(testForm);
    }

    /** Verifies the exact status message returned by the service. */
    @Test
    public void testGetRunningStatus() {
        String status = tradeFormService.getRunningStatus();
        String expectedStatus = "TradeForm Service is running";
        Assertions.assertEquals(expectedStatus, status);
    }

    /** Verifies that a persisted trade form can be retrieved by its generated identifier. */
    @Test
    public void testGetTradeFormByPositiveId() {

        TradeForm tradeForm = tradeFormService.getTradeFormById(testForm.getId());
        Assertions.assertNotNull(tradeForm);
        Assertions.assertEquals(testForm.getId(), tradeForm.id());
        Assertions.assertEquals(TradeFormStatus.PENDING, tradeForm.status());
    }

    /** Verifies that requesting a trade form with a negative identifier is rejected. */
    @Test
    public void testGetTradeFormByNegativeId() {
        int negativeId = -1;
        Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> tradeFormService.getTradeFormById(negativeId));
    }
}
