package io.alienhead.kleuth

import io.alienhead.kleuth.annotations.request.Get
import io.alienhead.kleuth.annotations.request.Post
import io.alienhead.kleuth.annotations.request.Put
import net.pearx.kasechange.toKebabCase
import org.springframework.web.bind.annotation.RequestMethod

class ParsingUtils {

  companion object {
    fun fromClassName(name: String): RequestMethod? {
      val methods = RequestMethod.values()

      return methods.firstOrNull {
        val method = it.name.toLowerCase().capitalize()

        name.startsWith(method) || name.endsWith(method)
      }
    }

    private fun fromAnnotation(annotation: Annotation): RequestMethod? =
      when (annotation) {
        is Get -> RequestMethod.GET
        is Post -> RequestMethod.POST
        is Put -> RequestMethod.PUT
        else -> null
      }

    fun findRequestMethodAnnotation(annotations: List<Annotation>): RequestMethod? {
      val annotation = annotations.firstOrNull {
        it is Get || it is Post || it is Put
      }

      return if (annotation != null) fromAnnotation(annotation) else null
    }
  }
}

fun String.removeRootPathFromPath(rootPath: String) = this.replace(rootPath.toKebabCase(), "")
fun String.removeClassName(className: String) = this.replace(className, "")
fun String.replacePackageSeparator() = this.replace(".", "/")
