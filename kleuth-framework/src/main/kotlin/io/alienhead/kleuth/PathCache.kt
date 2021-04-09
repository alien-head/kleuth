package io.alienhead.kleuth

import org.springframework.web.bind.annotation.PathVariable
import kotlin.reflect.KFunction
import kotlin.reflect.full.findAnnotation

/**
 * A cache of [PathInfo] objects that store the context of a url path and path variables for mapping requests
 *
 * @param pathToRoot the url path to remove from the path
 */
class PathCache(private val pathToRoot: String) {
  private val pathCache = mutableListOf<PathInfo>()

  /**
   * Retrieves the matching path from the cache, if not found, cache the path.
   *
   * @param originalPath the original package path without path variables
   * @param handlerFunction the handler function for a request method handler in a route class
   */
  internal fun getOrCache(originalPath: String, handlerFunction: KFunction<*>): String {
    var setPath = originalPath
    /*
     * Get all the names of path variables in the route handler.
     * Spring allows the user to specify a name or value instead of the parameter name for mapping purposes
     * so we have to check if those are set
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

    setPath = setPath.kebabCase()

    val matchingPathInfo = bestMatchingPath(setPath, routePathVariables)

    if (matchingPathInfo != null) {
      setPath = setPath.replace(matchingPathInfo.originalPath.kebabCase(), matchingPathInfo.newPath)

      // It's possible the user has more path variables than the matching cached path
      val missingPathVariables = routePathVariables - matchingPathInfo.pathVariables

      if (missingPathVariables.isNotEmpty() || setPath != matchingPathInfo.newPath) {
        /* If there are any path variables not found in the matched info,
         * or the path is longer,
         * then they must be appended to the path
         */
        val pathWithVariables = setPath + missingPathVariables.appendToPath()

        // This path must be cached since it has additional path variables
        pathCache.add(PathInfo(originalPath.kebabCase(), pathWithVariables, routePathVariables))

        setPath = pathWithVariables
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

  /**
   *   Check if there is a path already for the original path (folder path)
   *   that also includes the same path variables
   *
   *   @param pathToMatch the path to be checked against
   *   @param pathVariables the list of path variables in the route to check
   */
  private fun bestMatchingPath(pathToMatch: String, pathVariables: List<String>): PathInfo? {
    val matchingPaths = pathCache.filter { pathInfo ->
      pathToMatch.contains(pathInfo.originalPath.kebabCase()) && pathVariables.containsAll(pathInfo.pathVariables)
    }

    // The longest path and most path variables should be the immediate "parent" path
    return matchingPaths
      .sortedWith(
        compareBy(
          { it.originalPath.length },
          { it.pathVariables.size }
        )
      )
      .reversed()
      .firstOrNull()
  }
}
