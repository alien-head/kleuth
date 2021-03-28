package io.alienhead.kleuth

import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.RequestMethod

internal data class RouteOptions(
  val path: String,
  val method: RequestMethod,
  val produces: String = MediaType.APPLICATION_JSON_VALUE,
  val consumes: String = "",
)
