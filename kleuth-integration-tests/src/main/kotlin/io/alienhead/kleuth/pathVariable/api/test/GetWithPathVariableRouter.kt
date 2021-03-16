package io.alienhead.kleuth.pathVariable.api.test

import io.alienhead.kleuth.annotations.RouteController
import io.alienhead.kleuth.resource.TestResource
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PathVariable

@RouteController
class GetWithPathVariableRouter {
  fun handler(@PathVariable testId: String): ResponseEntity<TestResource> {
    return ResponseEntity.ok(TestResource("testId: $testId"))
  }
}
