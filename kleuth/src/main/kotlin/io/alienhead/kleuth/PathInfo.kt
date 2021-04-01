package io.alienhead.kleuth

/**
 * A class that stores the context of a path for mapping other paths
 *
 * @param originalPath the original package path of the class
 * @param newPath the formatted path that includes path variables
 * @param pathVariables the list of path variables on the path
 */
internal data class PathInfo(
  val originalPath: String,
  val newPath: String,
  val pathVariables: List<String>
)
