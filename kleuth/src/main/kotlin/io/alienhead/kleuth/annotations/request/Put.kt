package io.alienhead.kleuth.annotations.request

import org.springframework.http.MediaType

/**
 * Used to denote a function as a handler for this http request method in a Route or RouteController class
 *
 * @param produces Used to override the default produces value for the request mapping
 * @param consumes Used to override the default consumes value for the request mapping
 *
 * @see RouteController
 */
@Target(AnnotationTarget.FUNCTION)
@Retention
annotation class Put(
  val produces: String = MediaType.APPLICATION_JSON_VALUE,
  val consumes: String = MediaType.APPLICATION_JSON_VALUE
)
