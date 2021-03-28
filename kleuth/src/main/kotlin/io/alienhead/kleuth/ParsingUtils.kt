package io.alienhead.kleuth

import io.alienhead.kleuth.annotations.request.Delete
import io.alienhead.kleuth.annotations.request.Get
import io.alienhead.kleuth.annotations.request.Post
import io.alienhead.kleuth.annotations.request.Put
import net.pearx.kasechange.toKebabCase
import org.springframework.web.bind.annotation.RequestMethod

class ParsingUtils {

  companion object {
    fun String.ofRequestMethod(strictEquality: Boolean = false): RequestMethod? {
      val methods = RequestMethod.values()

      return methods.firstOrNull {
        val method = it.name.toLowerCase().capitalize()

        (strictEquality && this == method.toLowerCase()) || (this.startsWith(method) || this.endsWith(method))
      }
    }

    private fun fromAnnotation(annotation: Annotation?): RequestMethod? =
      when (annotation) {
        is Get -> RequestMethod.GET
        is Post -> RequestMethod.POST
        is Put -> RequestMethod.PUT
        is Delete -> RequestMethod.DELETE
        else -> null
      }

    fun findRequestMethodAnnotation(annotations: List<Annotation>): RequestMethod? {
      val annotation = annotations.firstOrNull {
        it is Get || it is Post || it is Put || it is Delete
      }

      return fromAnnotation(annotation)
    }
  }
}

fun String.toPath(className: String) = this.removeClassName(className).replacePackageSeparator()
fun String.removeRootPathFromPath(rootPath: String) = this.replace(rootPath.toKebabCase(), "")
fun String.removeClassName(className: String) = this.replace(".$className", "")
fun String.replacePackageSeparator() = this.replace(".", "/")
