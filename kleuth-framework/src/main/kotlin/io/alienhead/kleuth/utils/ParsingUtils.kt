package io.alienhead.kleuth.utils

import io.alienhead.kleuth.annotations.request.Content
import io.alienhead.kleuth.annotations.request.Delete
import io.alienhead.kleuth.annotations.request.Get
import io.alienhead.kleuth.annotations.request.Post
import io.alienhead.kleuth.annotations.request.Put
import org.springframework.web.bind.annotation.RequestMethod
import kotlin.reflect.KFunction
import kotlin.reflect.full.findAnnotation
import kotlin.reflect.full.hasAnnotation

/**
 * Checks whether a function has one of the Request Method annotations.
 */
internal fun KFunction<*>.hasRequestAnnotation() =
  this.hasAnnotation<Get>() ||
    this.hasAnnotation<Post>() ||
    this.hasAnnotation<Put>() ||
    this.hasAnnotation<Delete>()

/**
 * Gets the Spring RequestMethod enum from the RequestMethod annotation.
 */
internal fun Annotation?.fromAnnotation(): RequestMethod? =
  when (this) {
    is Get -> RequestMethod.GET
    is Post -> RequestMethod.POST
    is Put -> RequestMethod.PUT
    is Delete -> RequestMethod.DELETE
    else -> null
  }

/**
 * Gets the first Spring RequestMethod enum from a list of annotations on a function.
 */
internal fun List<Annotation>.findRequestMethodAnnotation(): RequestMethod? {
  val annotation = this.firstOrNull {
    it is Get || it is Post || it is Put || it is Delete
  }

  return annotation.fromAnnotation()
}

/**
 * Gets a Spring RequestMethod enum from a string. Defaults to check if the request method is at the start or end
 * of the string.
 *
 * @param strictEquality set this value if the entire string should equal the request method instead of at the start or the end
 */
internal fun String.ofRequestMethod(strictEquality: Boolean = false): RequestMethod? {
  val methods = RequestMethod.values()

  return methods.firstOrNull {
    val method = it.name.toLowerCase().capitalize()

    (strictEquality && this == method.toLowerCase()) || (this.startsWith(method) || this.endsWith(method))
  }
}

/**
 * Gets the produces and consumes values if they are set on the Request Method Annotation
 */
internal fun KFunction<*>.getProducesConsumes() =
  this.findAnnotation<Content>()?.let { annotation ->
    Pair(annotation.produces, annotation.consumes)
  } ?: this.findAnnotation<Get>()?.let { annotation ->
    Pair(annotation.produces, annotation.consumes)
  } ?: this.findAnnotation<Post>()?.let { annotation ->
    Pair(annotation.produces, annotation.consumes)
  } ?: this.findAnnotation<Put>()?.let { annotation ->
    Pair(annotation.produces, annotation.consumes)
  } ?: this.findAnnotation<Delete>()?.let { annotation ->
    Pair(annotation.produces, annotation.consumes)
  }

/**
 * Transforms a list of path variable strings into a path of path variables
 */
internal fun List<String>.appendToPath(): String {
  var path = ""
  this.forEach { pathVariable ->
    path += "/{$pathVariable}"
  }
  return path
}

/**
 * Transforms a fully qualified (package path) class name string into a path
 *
 * @param className the simple name of the class
 */
internal fun String.toPath(className: String) = this.removeClassName(className).replacePackageSeparator()

/**
 * Removes the root path from the string
 *
 * @param rootPath a package path
 */
internal fun String.removeRootPathFromPath(rootPath: String) = this.replace(rootPath.kebabCase(), "")

/**
 * Removes the class name from the string
 *
 * @param className the simple class name
 */
internal fun String.removeClassName(className: String) = this.replace(".$className", "")

/**
 * Replaces the package separator "." with a backslash
 */
internal fun String.replacePackageSeparator() = this.replace(".", "/")

/**
 * Replaces uppercase letters with "-LOWER_CASE_LETTER" ignoring pathVariables.
 * Example:
 *  route/pascalCase/{pathVariable}
 *  becomes
 *  route/pascal-case/{pathVariable}
 */
internal fun String.kebabCase(): String {
  var buildString = ""

  for (i in this.toCharArray().withIndex()) {
    var charToAdd = i.value.toString()

    if (i.value.isUpperCase()) {
      val substring = this.substring(0, i.index)

      if (substring.contains("{")) {
        if (substring.lastIndexOf("}") > substring.lastIndexOf("{")) {
          charToAdd = "-${i.value.toLowerCase()}"
        }
      } else {
        charToAdd = "-${i.value.toLowerCase()}"
      }
    }
    buildString += charToAdd
  }
  return buildString
}
