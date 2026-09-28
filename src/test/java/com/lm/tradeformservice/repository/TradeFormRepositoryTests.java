package com.lm.tradeformservice.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.lm.tradeformservice.dto.TradeFormStatus;
import com.lm.tradeformservice.entity.TradeFormEntity;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.jpa.test.autoconfigure.TestEntityManager;

/**
 * unit tests for the TradeFormRepository class.
 *
 * <p>TradeFormRepositoryTests
 */
@DataJpaTest(
        properties = {"spring.jpa.hibernate.ddl-auto=create-drop"},
        showSql = true)
public class TradeFormRepositoryTests {
    @Autowired ITradeFormRepository tradeFormRepository;
    @Autowired TestEntityManager testEntityManager;

    TradeFormEntity testForm;

    @AfterEach
    public void clean() {
        tradeFormRepository.delete(testForm);
    }

    /** Tests the save functionality of the TradeFormRepository. */
    @Test
    void testSaveTradeForm() {
        testForm = new TradeFormEntity(null, TradeFormStatus.PENDING);

        // Save the entity using the repository
        tradeFormRepository.save(testForm);
        assertEquals(
                testEntityManager.find(TradeFormEntity.class, testForm.getId()).getId(),
                testForm.getId());
    }

    /** Tests the findById method of the TradeFormRepository. */
    @Test
    void testFindById() {
        testForm = new TradeFormEntity(null, TradeFormStatus.PENDING);
        tradeFormRepository.save(testForm);

        // Retrieve the entity by ID using the repository
        TradeFormEntity retrievedEntity =
                tradeFormRepository.findById(testForm.getId()).orElse(null);

        // Assert that the retrieved entity is not null and has the expected values
        assertEquals(retrievedEntity.getId(), testForm.getId());
        assertEquals(retrievedEntity.getStatus(), testForm.getStatus());
    }

    /** Tests the update functionality of the TradeFormRepository. */
    @Test
    void testUpdateTradeFormStatus() {
        testForm = new TradeFormEntity(null, TradeFormStatus.PENDING);
        tradeFormRepository.save(testForm);
        // Update the status of the entity
        testForm.setStatus(TradeFormStatus.APPROVED);
        tradeFormRepository.save(testForm);

        // Retrieve the updated entity by ID using the repository
        TradeFormEntity updatedEntity = tradeFormRepository.findById(testForm.getId()).orElse(null);

        // Assert that the updated entity is not null and has the expected values
        assertEquals(updatedEntity.getId(), testForm.getId());
        assertEquals(updatedEntity.getStatus(), TradeFormStatus.APPROVED);
    }
}
