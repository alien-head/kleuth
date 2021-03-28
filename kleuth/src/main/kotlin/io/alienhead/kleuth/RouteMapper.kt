package io.alienhead.kleuth

import io.alienhead.kleuth.annotations.Route
import io.alienhead.kleuth.annotations.RouteController
import io.alienhead.kleuth.config.KleuthProperties
import org.slf4j.LoggerFactory
import org.springframework.context.ApplicationContext
import org.springframework.http.MediaType
import org.springframework.web.servlet.mvc.method.RequestMappingInfo
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping
import java.lang.reflect.Method
import java.time.Duration
import java.time.LocalDateTime
import javax.annotation.PostConstruct
import kotlin.reflect.full.findAnnotation
import kotlin.reflect.full.functions
import kotlin.reflect.jvm.javaMethod

class RouteMapper(
  private val handlerMapping: RequestMappingHandlerMapping,
  private val context: ApplicationContext,
  private var properties: KleuthProperties = KleuthProperties()
) {
  private val logger = LoggerFactory.getLogger(RouteMapper::class.java)
  private val pathCache = PathCache(properties.pathToRoot)

  @PostConstruct
  internal fun mapRoutes() {
    // Ensure the user has set the path to root property
    if (properties.pathToRoot.isEmpty()) {
      logger.error("Cannot build routes. The 'pathToRoot' property has not been set.")
      return
    }

    val startTime = LocalDateTime.now()

    val routes = getRoutes()
    val routeCount = processRoutes(routes)

    val timeSpent = Duration.between(startTime, LocalDateTime.now())
    logger.info("Took ${timeSpent.toMillis()} ms to map $routeCount routes.")
  }

  private fun getRoutes(): Map<String, Any> {
    return context.getBeansWithAnnotation(Route::class.java) + context.getBeansWithAnnotation(RouteController::class.java)
  }

  private fun processRoutes(routes: Map<String, Any>): Int {
    val routeHandlers = routes.toRouteHandlers(properties.pathToRoot)

    logger.info("Discovered ${routeHandlers.size} possible routes.")

    // Routes with an overridden path must be handled differently
    val normalRoutes = processRouteHandlersWithOverride(routeHandlers)

    // Process everything else
    processRouteHandlers(normalRoutes)

    return routeHandlers.size
  }

  private fun getRoutesWithOverride(routes: List<RouteHandler>) =
    routes.filter {
      !(it.handlerInstance::class.findAnnotation<Route>()?.path.isNullOrEmpty()) ||
        !(it.handlerInstance::class.findAnnotation<RouteController>()?.path.isNullOrEmpty())
    }

  private fun processRouteHandlersWithOverride(routes: List<RouteHandler>): List<RouteHandler> {
    val overrideRoutes = getRoutesWithOverride(routes)

    logger.info("Found ${overrideRoutes.size} routes with overridden paths.")

    overrideRoutes.forEach { processRouteHandler(it, true) }

    return routes - overrideRoutes
  }

  private fun processRouteHandler(routeHandler: RouteHandler, override: Boolean = false) {
    // get all route handler functions
    val requestMethodHandlers = routeHandler.handlerInstance::class.functions.filter {
      it.name == "handler" ||
        it.name.ofRequestMethod(true) != null ||
        it.hasRequestAnnotation()
    }

    // We only need to build a path using the first function because they should all have the same route
    val path = if (override) {
      routeHandler.handlerInstance::class.findAnnotation<Route>()?.path
        ?: routeHandler.handlerInstance::class.findAnnotation<RouteController>()?.path
    } else {
      pathCache.getOrCache(routeHandler.path, requestMethodHandlers.first())
    }

    if (path != null) {
      requestMethodHandlers.forEach {
        val requestMethod = when {
          it.name == "handler" -> {
            routeHandler.handlerInstance::class.simpleName!!.ofRequestMethod()
          }
          it.name.ofRequestMethod(true) != null -> {
            it.name.ofRequestMethod(true)
          }
          else -> {
            it.annotations.findRequestMethodAnnotation()
          }
        }

        val producesConsumes = it.getProducesConsumes()

        val routeOptions = RouteOptions(
          path,
          requestMethod!!,
          producesConsumes?.first ?: MediaType.APPLICATION_JSON_VALUE,
          producesConsumes?.second ?: ""
        )
        mapRoute(routeHandler.handlerInstance, it.javaMethod!!, routeOptions)
      }
    }
  }

  private fun processRouteHandlers(routes: List<RouteHandler>) {
    routes.sortRoutes().forEach { processRouteHandler(it) }
  }

  private fun mapRoute(
    routeHandlerInstance: Any,
    routeHandlerFunction: Method,
    routeOptions: RouteOptions
  ) {
    val requestMappingInfo = RequestMappingInfo
      .paths(routeOptions.path)
      .methods(routeOptions.method)

    requestMappingInfo.produces(routeOptions.produces)

    if (routeOptions.consumes.isNotEmpty()) requestMappingInfo.consumes(routeOptions.consumes)

    handlerMapping.registerMapping(
      requestMappingInfo.build(),
      routeHandlerInstance,
      routeHandlerFunction
    )
  }
}
