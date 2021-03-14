package io.alienhead.kleuth

import org.springframework.web.bind.annotation.RequestMethod

data class RouteOptions(
  val path: String,
  val method: RequestMethod
)
