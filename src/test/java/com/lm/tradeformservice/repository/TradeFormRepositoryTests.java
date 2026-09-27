package com.lm.tradeformservice.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.lm.tradeformservice.dto.TradeFormStatus;
import com.lm.tradeformservice.entity.TradeFormEntity;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
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

    @BeforeEach
    public void configure() {
        testForm = new TradeFormEntity(null, TradeFormStatus.PENDING);
        // Persist immediately so testForm.getId() is populated for every test
        // that relies on it (e.g. testFindById), and so clean() has a real
        // row to delete.
        tradeFormRepository.save(testForm);
    }

    @AfterEach
    public void clean() {
        tradeFormRepository.delete(testForm);
    }

    /** Tests the save functionality of the TradeFormRepository. */
    @Test
    void testSaveTradeForm() {

        // Save the entity using the repository
        tradeFormRepository.save(testForm);
        assertEquals(
                testEntityManager.find(TradeFormEntity.class, testForm.getId()).getId(),
                testForm.getId());
    }

    /** Tests the findById method of the TradeFormRepository. */
    @Test
    void testFindById() {

        // Retrieve the entity by ID using the repository
        TradeFormEntity retrievedEntity =
                tradeFormRepository.findById(testForm.getId()).orElse(null);

        // Assert that the retrieved entity is not null and has the expected values
        assertEquals(retrievedEntity.getId(), testForm.getId());
        assertEquals(retrievedEntity.getStatus(), testForm.getStatus());
    }

    /** Tests the findById method of the TradeFormRepository when the entity is not found. */
    @Test
    void testFindByIdNotFound() {
        // Attempt to retrieve an entity with a non-existent ID
        TradeFormEntity retrievedEntity = tradeFormRepository.findById(Long.MAX_VALUE).orElse(null);

        // Assert that the retrieved entity is null (not found)
        assertEquals(retrievedEntity, null);
    }

    /** Tests the update functionality of the TradeFormRepository. */
    @Test
    void testUpdateTradeFormStatus() {
        // Create a new TradeFormEntity
        TradeFormEntity tradeFormEntity = new TradeFormEntity(null, TradeFormStatus.PENDING);

        // Save the entity using the repository
        tradeFormRepository.save(tradeFormEntity);

        // Update the status of the entity
        tradeFormEntity.setStatus(TradeFormStatus.APPROVED);
        tradeFormRepository.save(tradeFormEntity);

        // Retrieve the updated entity by ID using the repository
        TradeFormEntity updatedEntity =
                tradeFormRepository.findById(tradeFormEntity.getId()).orElse(null);

        // Assert that the updated entity is not null and has the expected values
        assertEquals(updatedEntity.getId(), tradeFormEntity.getId());
        assertEquals(updatedEntity.getStatus(), TradeFormStatus.APPROVED);
    }

    /** Tests update on not found entity */
    @Test
    void testUpdadeNull() {
        // Attempt to retrieve an entity with a non-existent ID
        TradeFormEntity updatedEntity = tradeFormRepository.findById(Long.MAX_VALUE).orElse(null);

        assertThrows(
                NullPointerException.class,
                () -> {
                    updatedEntity.setStatus(TradeFormStatus.APPROVED);
                });
    }
}
