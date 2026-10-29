package com.lm.tradeformservice.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.lm.tradeformservice.dto.TradeFormStatus;
import com.lm.tradeformservice.entity.TradeFormEntity;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase;
import org.springframework.boot.jpa.test.autoconfigure.TestEntityManager;

/**
 * PostgreSQL-backed slice tests for {@link ITradeFormRepository}.
 *
 * <p>Verifies entity insertion, retrieval, and status updates using the JPA test slice and {@link
 * TestEntityManager}. The test uses the configured external database rather than replacing it with
 * an embedded database.
 */
@DataJpaTest(
        properties = {"spring.jpa.hibernate.ddl-auto=create-drop"},
        showSql = true)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class TradeFormRepositoryTests {

    /** Repository under test, provided by Spring Data JPA. */
    @Autowired ITradeFormRepository tradeFormRepository;

    /** Entity manager helper used to confirm persisted state. */
    @Autowired TestEntityManager testEntityManager;

    /** Entity created by each test and removed during cleanup. */
    TradeFormEntity testForm;

    /** Removes the entity created by the test to keep the database fixture clean. */
    @AfterEach
    public void clean() {
        tradeFormRepository.delete(testForm);
    }

    /** Verifies that saving an entity assigns an ID and makes it visible to the entity manager. */
    @Test
    void testSaveTradeForm() {
        testForm = new TradeFormEntity(null, TradeFormStatus.PENDING);

        // Save the entity using the repository
        tradeFormRepository.save(testForm);
        assertEquals(
                testEntityManager.find(TradeFormEntity.class, testForm.getId()).getId(),
                testForm.getId());
    }

    /** Verifies that a saved entity can be retrieved with its ID and status intact. */
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

    /** Verifies that saving a modified entity persists its updated status. */
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
