package io.alienhead.kleuth.annotations

/**
 * Used to denote a class as a Kleuth route when the class name is an http request method (Get, Post, Put).
 * Must be used in conjunction with a Spring RestController Annotation to specify the bean name.
 * If the route handler function is named "handler", the class name must begin or end with an http request method. (GetPizza, PizzaPost, etc.)
 *
 * @param path set this variable to override the dynamic route Kleuth would build. Must be formatted as a url.
 *             All nested routes must also set this variable.
 * @see Route
 */
@Target(AnnotationTarget.CLASS)
@Retention
annotation class RequestMethod(val path: String = "")
