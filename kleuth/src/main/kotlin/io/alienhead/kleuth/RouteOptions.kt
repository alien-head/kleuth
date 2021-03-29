package io.alienhead.kleuth

import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.RequestMethod

/**
 * This object stores the context needed for mapping a route
 *
 * @param path the url path for the request mapping
 * @param method the Spring Request Method enum for the mapping
 * @param produces the produces value for the mapping
 * @param consumes the consumes value for the mapping
 */
internal data class RouteOptions(
  val path: String,
  val method: RequestMethod,
  val produces: String = MediaType.APPLICATION_JSON_VALUE,
  val consumes: String = "",
)
