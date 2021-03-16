package io.alienhead.kleuth.pathVariables.api.test.child

import io.alienhead.kleuth.annotations.RouteController
import io.alienhead.kleuth.resource.TestResource
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PathVariable

@RouteController
class GetChildPathVariable {
  fun handler(@PathVariable testId: String, @PathVariable childId: String): ResponseEntity<TestResource> {
    return ResponseEntity.ok(TestResource("testId: $testId, childId: $childId"))
  }
}
