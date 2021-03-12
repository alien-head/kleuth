package io.alienhead.kleuth.config

import io.alienhead.kleuth.RouteMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.ApplicationContext
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping

/**
 *
 */
@Configuration
@EnableConfigurationProperties(value = [RouteMapperProperties::class])
class KleuthAutoConfiguration(
    @Autowired private val handlerMapping: RequestMappingHandlerMapping,
    @Autowired private val context: ApplicationContext,
    @Autowired private val properties: RouteMapperProperties
) {

    @Bean
    @ConditionalOnMissingBean
    fun routeMapper(): RouteMapper = RouteMapper(handlerMapping, context, properties)
}
