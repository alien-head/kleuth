package io.alienhead.kleuth.mapper

import io.alienhead.kleuth.annotations.request.Delete
import io.alienhead.kleuth.annotations.request.Get
import io.alienhead.kleuth.annotations.request.Post
import io.alienhead.kleuth.annotations.request.Put
import io.alienhead.kleuth.utils.ofRequestMethod
import io.alienhead.kleuth.utils.toPath
import org.springframework.web.bind.annotation.PathVariable
import kotlin.reflect.full.findAnnotation
import kotlin.reflect.full.functions
import kotlin.reflect.full.hasAnnotation

/**
 * Sorts [RouteHandler] classes alphabetically by package path and number of path variables
 */
internal fun List<RouteHandler>.sortRoutes(): List<RouteHandler> {
  return this.sortedWith(
    compareBy(
      { it.path },
      {
        it.handlerInstance::class.functions.first { function ->
          function.name == "handler" ||
            function.name.ofRequestMethod(true) != null ||
            (
              function.hasAnnotation<Get>() ||
                function.hasAnnotation<Post>() ||
                function.hasAnnotation<Put>() ||
                function.hasAnnotation<Delete>()
              )
        }.parameters.filter { parameter ->
          parameter.findAnnotation<PathVariable>() != null
        }.size
      }
    )
  )
}

/**
 * Transforms a list of beans to route handler objects
 *
 * @param filterPath the package path to filter out potential beans
 */
internal fun Map<String, Any>.toRouteHandlers(filterPath: String) =
  this.map {
    RouteHandler(
      it.value,
      it.value::class.qualifiedName!!.toPath(it.value::class.simpleName!!)
    )
  }.filter { it.path.contains(filterPath) }
