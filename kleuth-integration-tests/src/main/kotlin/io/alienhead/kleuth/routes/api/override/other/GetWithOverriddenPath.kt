package io.alienhead.kleuth.routes.api.override.other

import io.alienhead.kleuth.annotations.Route
import io.alienhead.kleuth.resource.TestResource
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PathVariable

@Route("/overridden-path/{testId}")
class GetWithOverriddenPath {
  fun handler(@PathVariable testId: String): ResponseEntity<TestResource> {
    return ResponseEntity.ok(TestResource("testId: $testId"))
  }
}
