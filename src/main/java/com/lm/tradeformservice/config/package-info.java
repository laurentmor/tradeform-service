/**
 * Spring configuration for application-wide infrastructure beans.
 *
 * <p>{@link com.lm.tradeformservice.config.MapperConfig} registers a ModelMapper configured with
 * strict property matching. Inject the shared bean rather than constructing a mapper in each
 * component.
 *
 * <h2>Mapping example</h2>
 *
 * <pre>{@code
 * @Service
 * class ExampleMapperUser {
 *     private final ModelMapper modelMapper;
 *
 *     ExampleMapperUser(ModelMapper modelMapper) {
 *         this.modelMapper = modelMapper;
 *     }
 *
 *     TradeForm map(TradeFormEntity entity) {
 *         return modelMapper.map(entity, TradeForm.class);
 *     }
 * }
 * }</pre>
 *
 * <p>Strict matching is intended to avoid silently mapping unrelated properties. The configured
 * bean is available through Spring dependency injection.
 */
package com.lm.tradeformservice.config;
