package io.alienhead.kleuth.config

import org.springframework.context.annotation.Import

@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
@Import(RouteMapperAutoConfiguration::class)
annotation class EnableRouteMapper
