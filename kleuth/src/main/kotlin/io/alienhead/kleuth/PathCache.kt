package io.alienhead.kleuth

import net.pearx.kasechange.toKebabCase
import org.springframework.web.bind.annotation.PathVariable
import kotlin.reflect.KFunction
import kotlin.reflect.full.findAnnotation

class PathCache(private val pathToRoot: String) {
  private val pathCache = mutableListOf<PathInfo>()

  internal fun getOrCache(originalPath: String, handlerFunction: KFunction<*>): String {
    var setPath = originalPath
    /*
          Get all the names of path variables in the route handler.
          Spring allows the user to specify a name or value instead of the parameter name for mapping purposes
          so we have to check if those are set
         */
    val routePathVariables: List<String> = handlerFunction.parameters.mapNotNull { parameter ->
      parameter.findAnnotation<PathVariable>()?.let { pathVariable ->
        when {
          pathVariable.name.isNotEmpty() -> pathVariable.name
          pathVariable.value.isNotEmpty() -> pathVariable.value
          else -> parameter.name
        }
      }
    }

    setPath = setPath.toKebabCase()

    /*
      Check if there is a path already for the original path (folder path)
      that also includes the same path variables
     */
    val matchingPathInfo = pathCache.firstOrNull { pathInfo ->
      setPath.contains(pathInfo.originalPath.toKebabCase()) && routePathVariables.containsAll(pathInfo.pathVariables)
    }

    if (matchingPathInfo != null) {
      setPath = setPath.replace(matchingPathInfo.originalPath, matchingPathInfo.newPath)
      // It's possible the user has more path variables than the matching cached path
      val missingPathVariables = routePathVariables - matchingPathInfo.pathVariables

      if (missingPathVariables.isNotEmpty()) {
        // If there are any path variables not found in the matched info,
        // then they must be appended to the path
        setPath += missingPathVariables.appendToPath()

        // This path must be cached since it has additional path variables
        pathCache.add(PathInfo(matchingPathInfo.originalPath, setPath, routePathVariables))
      } else {
        // This route's path and path variables are the same as another so we do not cache again.
        setPath = matchingPathInfo.newPath
      }
    } else {
      // We did not find an existing path, so this one is new and must be cached.
      val newPath = setPath + routePathVariables.appendToPath()

      // if no matching path then this is a new path
      pathCache.add(PathInfo(setPath, newPath, routePathVariables))

      setPath = newPath
    }

    return setPath.removeRootPathFromPath(pathToRoot)
  }
}
