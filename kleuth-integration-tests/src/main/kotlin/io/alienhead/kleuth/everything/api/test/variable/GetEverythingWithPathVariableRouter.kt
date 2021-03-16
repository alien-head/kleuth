package io.alienhead.kleuth.everything.api.test.variable

import io.alienhead.kleuth.annotations.RouteController
import io.alienhead.kleuth.resource.TestResource
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PathVariable

@RouteController
class GetEverythingWithPathVariableRouter {
  fun handler(@PathVariable testId: String): ResponseEntity<TestResource> {
    return ResponseEntity.ok(TestResource("testId: $testId"))
  }
}
