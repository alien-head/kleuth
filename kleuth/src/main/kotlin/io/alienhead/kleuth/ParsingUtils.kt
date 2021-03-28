package io.alienhead.kleuth

import io.alienhead.kleuth.annotations.request.Delete
import io.alienhead.kleuth.annotations.request.Get
import io.alienhead.kleuth.annotations.request.Post
import io.alienhead.kleuth.annotations.request.Put
import net.pearx.kasechange.toKebabCase
import org.springframework.web.bind.annotation.RequestMethod
import kotlin.reflect.KFunction
import kotlin.reflect.full.findAnnotation
import kotlin.reflect.full.hasAnnotation

internal fun KFunction<*>.hasRequestAnnotation() =
  this.hasAnnotation<Get>() ||
    this.hasAnnotation<Post>() ||
    this.hasAnnotation<Put>() ||
    this.hasAnnotation<Delete>()

internal fun Annotation?.fromAnnotation(): RequestMethod? =
  when (this) {
    is Get -> RequestMethod.GET
    is Post -> RequestMethod.POST
    is Put -> RequestMethod.PUT
    is Delete -> RequestMethod.DELETE
    else -> null
  }

internal fun List<Annotation>.findRequestMethodAnnotation(): RequestMethod? {
  val annotation = this.firstOrNull {
    it is Get || it is Post || it is Put || it is Delete
  }

  return annotation.fromAnnotation()
}

internal fun String.ofRequestMethod(strictEquality: Boolean = false): RequestMethod? {
  val methods = RequestMethod.values()

  return methods.firstOrNull {
    val method = it.name.toLowerCase().capitalize()

    (strictEquality && this == method.toLowerCase()) || (this.startsWith(method) || this.endsWith(method))
  }
}

internal fun KFunction<*>.getProducesConsumes() =
  this.findAnnotation<Get>()?.let { annotation ->
    Pair(annotation.produces, annotation.consumes)
  } ?: this.findAnnotation<Post>()?.let { annotation ->
    Pair(annotation.produces, annotation.consumes)
  } ?: this.findAnnotation<Put>()?.let { annotation ->
    Pair(annotation.produces, annotation.consumes)
  } ?: this.findAnnotation<Delete>()?.let { annotation ->
    Pair(annotation.produces, annotation.consumes)
  }

internal fun List<String>.appendToPath(): String {
  var path = ""
  this.forEach { pathVariable ->
    path += "/{$pathVariable}"
  }
  return path
}

internal fun String.toPath(className: String) = this.removeClassName(className).replacePackageSeparator()
internal fun String.removeRootPathFromPath(rootPath: String) = this.replace(rootPath.toKebabCase(), "")
internal fun String.removeClassName(className: String) = this.replace(".$className", "")
internal fun String.replacePackageSeparator() = this.replace(".", "/")
