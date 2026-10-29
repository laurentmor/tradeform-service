package com.lm.tradeformservice.config;

import static org.modelmapper.convention.MatchingStrategies.STRICT;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configures application-wide object mapping.
 *
 * <p>The mapper uses strict property matching so a source property is only mapped when its name and
 * type match a destination property according to ModelMapper's strict strategy.
 */
@Configuration
public class MapperConfig {

    /**
     * Creates the shared strict-matching {@link ModelMapper} bean.
     *
     * @return a ModelMapper configured for strict property matching
     */
    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        // Set the matching strategy to STRICT for precise mapping between source and destination
        // objects

        modelMapper.getConfiguration().setMatchingStrategy(STRICT);

        return modelMapper;
    }
}
