package io.alienhead.kleuth

import io.alienhead.kleuth.annotations.Route
import io.alienhead.kleuth.annotations.RouteController
import io.alienhead.kleuth.config.RouteMapperProperties
import org.springframework.boot.context.event.ApplicationReadyEvent
import org.springframework.context.ApplicationContext
import org.springframework.context.event.EventListener
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.servlet.mvc.method.RequestMappingInfo
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping


class RouteMapper(
  private val handlerMapping: RequestMappingHandlerMapping,
  private val context: ApplicationContext,
  private var properties: RouteMapperProperties = RouteMapperProperties()
) {

  @EventListener(ApplicationReadyEvent::class)
  fun mapRoutes() {
    // Ensure the user has set the path to package property
    if (properties.pathToPackage.isEmpty()) return

    val routes = getRoutes()

    mapRoutes(routes)

    if (properties.health.enabled) {
      addHealthEndpoint()
    }
  }

  private fun getRoutes(): Map<String, Any> {
    return context.getBeansWithAnnotation(Route::class.java) + context.getBeansWithAnnotation(RouteController::class.java)
  }

  private fun mapRoutes(routes: Map<String, Any>) {
    routes.forEach {
      val classForRoute = it.value::class.java
      val requestMethod = RequestMethodUtils.fromClassName(classForRoute.simpleName) ?: return@forEach

      val urlPath = packagePathToUrlPath(classForRoute.canonicalName, classForRoute.simpleName)
      val routeOptions = RouteOptions(urlPath, requestMethod)

      mapRoute(it.value, routeOptions)
    }
  }

  private fun mapRoute(routeInstance: Any, routeOptions: RouteOptions) {
    val handlerClass = routeInstance::class

    val handlerFunction = handlerClass.java.declaredMethods.firstOrNull {
      it.name == "handler"
    } ?: return

    val requestMappingInfo = RequestMappingInfo
      .paths(routeOptions.path)
      .methods(routeOptions.method)
      .produces(MediaType.APPLICATION_JSON_VALUE)

    if (routeOptions.method in listOf(RequestMethod.POST, RequestMethod.PUT)) {
      requestMappingInfo.consumes(MediaType.APPLICATION_JSON_VALUE)
    }

    handlerMapping.registerMapping(
      requestMappingInfo.build(),
      routeInstance,
      handlerFunction
    )
  }

  private fun packagePathToUrlPath(path: String, className: String): String {
    return path
      .replace(".$className", "")
      .replace(properties.pathToPackage, "")
      .replace(".", "/")
      .replace("_", "-")
  }

  private fun addHealthEndpoint() {

    val handlerFunction = this::class.java.declaredMethods.firstOrNull {
      it.name == "healthk"
    } ?: return

    val requestMappingInfo = RequestMappingInfo
      .paths("/${properties.health.endpoint}")
      .methods(RequestMethod.GET)
      .produces(MediaType.APPLICATION_JSON_VALUE)

    handlerMapping.registerMapping(
      requestMappingInfo.build(),
      this,
      handlerFunction
    )
  }

  // Default health endpoint. This is configured last to ensure all routes have been mapped.
  fun healthk(): ResponseEntity<Unit> = ResponseEntity(HttpStatus.OK)
}
