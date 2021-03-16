package io.alienhead.kleuth

data class PathInfo(
  val originalPath: String,
  val newPath: String,
  val pathVariables: List<String>
)
