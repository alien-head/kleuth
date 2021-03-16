package io.alienhead.kleuth.annotations.request

/*
    Use this annotation along with a @RestController annotation (with unique name!! example: @RestController("my-route")
    to use descriptive class names (get, post, put instead of GetMyRoute, PostMyRoute)
 */
@Target(AnnotationTarget.FUNCTION)
@Retention
annotation class Put
