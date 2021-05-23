package io.alienhead.kleuth.annotations.request

import org.springframework.http.MediaType

/**
 * Used to denote the produce and consumes content types for the route.
 *
 * @param produces Used to override the default produces value for the request mapping
 * @param consumes Used to override the default consumes value for the request mapping
 *
 * @see Route
 */
@Target(AnnotationTarget.FUNCTION)
@Retention
annotation class Content(
  val produces: String = MediaType.APPLICATION_JSON_VALUE,
  val consumes: String = ""
)
