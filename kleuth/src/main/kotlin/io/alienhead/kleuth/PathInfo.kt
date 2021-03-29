package io.alienhead.kleuth

internal data class PathInfo(
  val originalPath: String,
  val newPath: String,
  val pathVariables: List<String>
)
