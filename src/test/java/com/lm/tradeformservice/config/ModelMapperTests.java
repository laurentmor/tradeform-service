package com.lm.tradeformservice.config;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.lm.tradeformservice.dto.TradeForm;
import com.lm.tradeformservice.dto.TradeFormStatus;
import com.lm.tradeformservice.entity.TradeFormEntity;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/** Integration tests for the application-wide ModelMapper configuration. */
@SpringBootTest
public class ModelMapperTests {

    /** Application-wide mapper configured by {@link MapperConfig}. */
    /** Application-wide ModelMapper bean under test. */
    @Autowired private ModelMapper tradeFormMapper;

    /** Verifies that the application configures ModelMapper with strict matching. */
    @Test
    public void testModelMapperUsesStrictMatching() {
        assertEquals(
                MatchingStrategies.STRICT,
                tradeFormMapper.getConfiguration().getMatchingStrategy());
    }

    /** Verifies that ModelMapper maps entity properties into the immutable record DTO. */
    @Test
    public void testModelMapperMapsEntityToTradeFormRecord() {
        TradeFormEntity entity = new TradeFormEntity(42L, TradeFormStatus.APPROVED);

        TradeForm tradeForm = tradeFormMapper.map(entity, TradeForm.class);

        assertEquals(42L, tradeForm.id());
        assertEquals(TradeFormStatus.APPROVED, tradeForm.status());
    }

    /** Verifies that ModelMapper maps record properties into the persistence entity. */
    @Test
    public void testModelMapperMapsTradeFormRecordToEntity() {
        TradeForm tradeForm = new TradeForm(42L, TradeFormStatus.REJECTED);

        TradeFormEntity entity = tradeFormMapper.map(tradeForm, TradeFormEntity.class);

        assertEquals(42L, entity.getId());
        assertEquals(TradeFormStatus.REJECTED, entity.getStatus());
    }
}
