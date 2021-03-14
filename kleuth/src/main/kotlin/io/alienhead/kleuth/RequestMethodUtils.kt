package io.alienhead.kleuth

import org.springframework.web.bind.annotation.RequestMethod

class RequestMethodUtils {

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
  }
}
