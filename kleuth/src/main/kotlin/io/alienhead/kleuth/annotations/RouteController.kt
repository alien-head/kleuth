package io.alienhead.kleuth.annotations

import org.springframework.web.bind.annotation.RestController

/**
 * Used to denote a class as a Kleuth route. If the route handler function is named "handler",
 * the class name must begin or end with an http request method. (GetPizza, PizzaPost, etc.)
 *
 * @param path set this variable to override the dynamic route Kleuth would build. Must be formatted as a url.
 *             All nested routes must also set this variable.
 * @see Route
 */
@Target(AnnotationTarget.CLASS)
@Retention
@RestController
annotation class RouteController(val path: String = "")
