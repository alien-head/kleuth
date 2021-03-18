package io.alienhead.kleuth

import io.alienhead.kleuth.annotations.Route
import io.alienhead.kleuth.annotations.RouteController
import io.alienhead.kleuth.annotations.request.Get
import io.alienhead.kleuth.annotations.request.Post
import io.alienhead.kleuth.annotations.request.Put
import io.alienhead.kleuth.config.KleuthProperties
import net.pearx.kasechange.toKebabCase
import org.slf4j.LoggerFactory
import org.springframework.context.ApplicationContext
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.servlet.mvc.method.RequestMappingInfo
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping
import java.lang.reflect.Method
import javax.annotation.PostConstruct
import kotlin.reflect.full.findAnnotation
import kotlin.reflect.full.functions
import kotlin.reflect.full.hasAnnotation
import kotlin.reflect.jvm.javaMethod

class RouteMapper(
  private val handlerMapping: RequestMappingHandlerMapping,
  private val context: ApplicationContext,
  private var properties: KleuthProperties = KleuthProperties()
) {
  private val logger = LoggerFactory.getLogger(RouteMapper::class.java)

  private var routesReady = false

  // @EventListener(ApplicationReadyEvent::class)
  @PostConstruct
  fun mapRoutes() {
    // Ensure the user has set the path to package property
    if (properties.pathToPackage.isEmpty()) return

    if (properties.health.enabled) {
      logger.info("Launching health endpoint at /${properties.health.endpoint} ")
      addHealthEndpoint()
    }

    val routes = getRoutes()

    val routeHandlers = routes.map {
      RouteHandler(
        it.value,
        it.value::class.qualifiedName!!
          .removeClassName(".${it.value::class.simpleName!!}")
          .replacePackageSeparator()
      )
    }.filter { it.path.contains(properties.pathToPackage) }

    logger.info("Discovered ${routeHandlers.size} possible routes.")

    // Routes with an overridden path must be handled differently
    val overrideRoutes = routeHandlers.filter {
      !(it.handlerInstance::class.findAnnotation<Route>()?.path.isNullOrEmpty()) ||
        !(it.handlerInstance::class.findAnnotation<RouteController>()?.path.isNullOrEmpty())
    }

    logger.info("Found ${overrideRoutes.size} routes with overridden paths.")

    overrideRoutes.forEach { processRouteHandlerWithOverride(it) }

    val normalRoutes = routeHandlers - overrideRoutes

    normalRoutes.sortRoutes().mapRoutes(properties.pathToPackage)
    routesReady = true

    // logger.info("$mappedRoutes routes have been mapped.")
  }

  private fun getRoutes(): Map<String, Any> {
    return context.getBeansWithAnnotation(Route::class.java) + context.getBeansWithAnnotation(RouteController::class.java)
  }

  private fun List<RouteHandler>.mapRoutes(rootToBasePath: String) {

    // A "cache" of already added paths. Needed to determine the placement of path variables
    val paths = mutableListOf<PathInfo>()

    // For all route handlers
    this.forEach {
      val routeHandlerClass = it.handlerInstance::class
      // We only need the first one because they all must match
      val firstHandlerFunction = routeHandlerClass.functions.firstOrNull { function ->
        function.name == "handler" ||
          (
            function.hasAnnotation<Get>() ||
              function.hasAnnotation<Post>() ||
              function.hasAnnotation<Put>()
            )
      }

      if (firstHandlerFunction != null) {
        /*
          Get all the names of path variables in the route handler.
          Spring allows the user to specify a name or value instead of the function name for mapping purposes
          so we have to check if those are set
         */
        val routePathVariables: List<String> = firstHandlerFunction.parameters.mapNotNull { parameter ->
          parameter.findAnnotation<PathVariable>()?.let { pathVariable ->
            when {
              pathVariable.name.isNotEmpty() -> pathVariable.name
              pathVariable.value.isNotEmpty() -> pathVariable.value
              else -> parameter.name
            }
          }
        }

        /*
          Check if there is a path already for the original path (folder path)
          that also includes the same path variables
         */
        val matchingPathInfo = paths.firstOrNull { pathInfo ->
          it.path.toKebabCase().contains(pathInfo.originalPath.toKebabCase()) && routePathVariables.containsAll(pathInfo.pathVariables)
        }

        it.path = it.path.toKebabCase()

        if (matchingPathInfo != null) {
          it.path = it.path.replace(matchingPathInfo.originalPath, matchingPathInfo.newPath)
          // It's possible the user has additional path variables than the matching cached path
          val missingPathVariables = routePathVariables - matchingPathInfo.pathVariables

          if (missingPathVariables.isNotEmpty()) {
            var pathToAppend = ""

            // If there are any path variables not found in the matched info,
            // then they must be appended to the path
            missingPathVariables.forEach { pathVariable ->
              pathToAppend += "/{$pathVariable}"
            }

            it.path += pathToAppend

            // This path must be cached since it has additional path variables
            paths.add(PathInfo(matchingPathInfo.originalPath, it.path, routePathVariables))
          } else {
            // This route's path and path variables are the same as another so we do not cache again.
            it.path = matchingPathInfo.newPath
          }
        } else {
          // We did not find an existing path, so this one is new and must be cached.
          var pathToAppend = ""

          routePathVariables.forEach { pathVariable ->
            pathToAppend += "/{$pathVariable}"
          }
          val newPath = it.path + pathToAppend

          // if no matching path then this is a new path
          paths.add(PathInfo(it.path, newPath, routePathVariables))

          it.path = newPath
        }

        it.path = it.path.removeRootPathFromPath(rootToBasePath)

        processRouteHandler(it)
      }
    }
  }

  private fun processRouteHandler(routeHandler: RouteHandler) {
    // get all route handler functions
    val requestMethodHandlers = routeHandler.handlerInstance::class.functions.filter {
      it.name == "handler" ||
        (
          it.hasAnnotation<Get>() ||
            it.hasAnnotation<Post>() ||
            it.hasAnnotation<Put>()
          )
    }

    requestMethodHandlers.forEach {
      val requestMethod = if (it.name == "handler") {
        ParsingUtils.fromClassName(routeHandler.handlerInstance::class.simpleName!!)
      } else {
        ParsingUtils.findRequestMethodAnnotation(it.annotations)
      }

      val routeOptions = RouteOptions(routeHandler.path, requestMethod!!)
      mapRoute(routeHandler.handlerInstance, it.javaMethod!!, routeOptions)
    }
  }

  private fun processRouteHandlerWithOverride(routeHandler: RouteHandler) {
    // get all route handler functions
    val requestMethodHandlers = routeHandler.handlerInstance::class.functions.filter {
      it.name == "handler" ||
        (
          it.hasAnnotation<Get>() ||
            it.hasAnnotation<Post>() ||
            it.hasAnnotation<Put>()
          )
    }

    requestMethodHandlers.forEach {
      val requestMethod = if (it.name == "handler") {
        ParsingUtils.fromClassName(routeHandler.handlerInstance::class.simpleName!!)
      } else {
        ParsingUtils.findRequestMethodAnnotation(it.annotations)
      }

      val overridePath = routeHandler.handlerInstance::class.findAnnotation<Route>()?.path
        ?: routeHandler.handlerInstance::class.findAnnotation<RouteController>()?.path

      if (overridePath != null) {
        val routeOptions = RouteOptions(overridePath, requestMethod!!)
        mapRoute(routeHandler.handlerInstance, it.javaMethod!!, routeOptions)
      }
      // TODO else Log bad route
    }
  }

  private fun mapRoute(routeHandlerInstance: Any, routeHandlerFunction: Method, routeOptions: RouteOptions) {
    val requestMappingInfo = RequestMappingInfo
      .paths(routeOptions.path)
      .methods(routeOptions.method)
      // TODO allow the user to override this
      .produces(MediaType.APPLICATION_JSON_VALUE)

    // TODO allow the user to override this
    if (routeOptions.method in listOf(RequestMethod.POST, RequestMethod.PUT)) {
      requestMappingInfo.consumes(MediaType.APPLICATION_JSON_VALUE)
    }

    handlerMapping.registerMapping(
      requestMappingInfo.build(),
      routeHandlerInstance,
      routeHandlerFunction
    )
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
  fun healthk(): ResponseEntity<Unit> =
    if (routesReady) ResponseEntity(HttpStatus.OK) else ResponseEntity(HttpStatus.SERVICE_UNAVAILABLE)
}
