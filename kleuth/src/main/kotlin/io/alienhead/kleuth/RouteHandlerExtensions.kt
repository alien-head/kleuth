package io.alienhead.kleuth

import io.alienhead.kleuth.annotations.request.Get
import io.alienhead.kleuth.annotations.request.Post
import io.alienhead.kleuth.annotations.request.Put
import org.springframework.web.bind.annotation.PathVariable
import kotlin.reflect.full.findAnnotation
import kotlin.reflect.full.functions
import kotlin.reflect.full.hasAnnotation

// sort by path alphabetically and number of path variables.
// get first function named "handler" or that has a request method annotation.
// count how many path variables it has
fun List<RouteHandler>.sortRoutes(): List<RouteHandler> {
  return this.sortedWith(
    compareBy(
      { it.path },
      {
        it.handlerInstance::class.functions.first { function ->
          function.name == "handler" ||
            (
              function.hasAnnotation<Get>() ||
                function.hasAnnotation<Post>() ||
                function.hasAnnotation<Put>()
              )
        }.parameters.filter { parameter ->
          parameter.findAnnotation<PathVariable>() != null
        }.size
      }
    )
  )
}
