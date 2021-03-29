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
 * Autoconfigures the Kleuth Route Mapper Bean
 *
 * @param handlerMapping The Spring RequestMappingHandlerMapping service. Needed to map request handlers.
 * @param context Spring's ApplicationContext. Needed to get Route and Route Controller Beans.
 * @param properties Kleuth properties as set in the application.properties files.
 *
 * @see KleuthProperties
 */
@Configuration
@EnableConfigurationProperties(value = [KleuthProperties::class])
class KleuthAutoConfiguration(
  @Autowired private val handlerMapping: RequestMappingHandlerMapping,
  @Autowired private val context: ApplicationContext,
  @Autowired private val properties: KleuthProperties
) {

  @Bean
  @ConditionalOnMissingBean
  fun routeMapper(): RouteMapper = RouteMapper(handlerMapping, context, properties)
}
