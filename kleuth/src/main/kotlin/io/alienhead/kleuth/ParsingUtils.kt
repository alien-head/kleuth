package io.alienhead.kleuth

import io.alienhead.kleuth.annotations.request.Get
import io.alienhead.kleuth.annotations.request.Post
import io.alienhead.kleuth.annotations.request.Put
import org.springframework.web.bind.annotation.RequestMethod

class ParsingUtils {

  companion object {
    fun fromClassName(name: String): RequestMethod? {
      val methods = RequestMethod.values()

      methods.forEach {
        val method = it.name.toLowerCase()

        var index = 0
        var indexOf = name.toLowerCase().indexOf(method, index)

        while (indexOf < name.length && indexOf > -1) {
          val methodLength = indexOf + method.length

          if (name.length == methodLength || (indexOf == 0 && name[methodLength].isUpperCase())) {
            return it
          } else {
            index = indexOf + method.length
            indexOf = name.toLowerCase().indexOf(method, index)
          }
        }
      }
      return null
    }

    fun fromAnnotation(annotation: Annotation): RequestMethod? =
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

fun String.removeRootPathFromRoutePath(rootPath: String): String {
  return this.replace(rootPath, "")
}

fun String.removeClassNameFromFullName(className: String) = this.replace(className, "")
