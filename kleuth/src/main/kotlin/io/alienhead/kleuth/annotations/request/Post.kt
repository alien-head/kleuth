package io.alienhead.kleuth.annotations.request

import org.springframework.http.MediaType

/*
    Use this annotation along with a @RestController annotation (with unique name!! example: @RestController("my-route")
    to use descriptive class names (get, post, put instead of GetMyRoute, PostMyRoute)
 */
@Target(AnnotationTarget.FUNCTION)
@Retention
annotation class Post(
  val produces: String = MediaType.APPLICATION_JSON_VALUE,
  val consumes: String = MediaType.APPLICATION_JSON_VALUE
)
