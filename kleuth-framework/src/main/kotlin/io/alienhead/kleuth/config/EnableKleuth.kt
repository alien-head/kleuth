package io.alienhead.kleuth.config

import org.springframework.context.annotation.Import

/**
 * Set this annotation on the Application class to setup Kleuth for route mapping
 *
 * @see KleuthAutoConfiguration
 */
@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
@Import(KleuthConfiguration::class)
annotation class EnableKleuth
