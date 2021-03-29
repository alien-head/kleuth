package io.alienhead.kleuth

/**
 * The object that stores the route handler class and path for mapping
 *
 * @param handlerInstance the instance of the Route or RouteController bean
 * @param path the original package path of the handlerInstance
 */
internal data class RouteHandler(
  val handlerInstance: Any,
  var path: String
)
