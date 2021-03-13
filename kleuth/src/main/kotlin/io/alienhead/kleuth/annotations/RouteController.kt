package io.alienhead.kleuth.annotations

import org.springframework.web.bind.annotation.RestController

/*
    Use this annotation if your route class is uniquely named (not just "get", "post", etc.)
    to hide the @RestController annotation
 */
@Target(AnnotationTarget.CLASS)
@Retention
@RestController
annotation class RouteController
