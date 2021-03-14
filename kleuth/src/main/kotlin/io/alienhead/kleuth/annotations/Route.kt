package io.alienhead.kleuth.annotations

/*
    Use this annotation along with a @RestController annotation (with unique name!! example: @RestController("my-route")
    to use descriptive class names (get, post, put instead of GetMyRoute, PostMyRoute)
 */
@Target(AnnotationTarget.CLASS)
@Retention
annotation class Route
