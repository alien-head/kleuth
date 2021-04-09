package io.alienhead.kleuth.routes.api.pathVariables.child

import io.alienhead.kleuth.annotations.Route
import io.alienhead.kleuth.resource.TestResource
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PathVariable

@Route
class GetChildByTestId {
  fun handler(@PathVariable testId: String): ResponseEntity<TestResource> {
    return ResponseEntity.ok(TestResource("Child with testId: $testId"))
  }
}
