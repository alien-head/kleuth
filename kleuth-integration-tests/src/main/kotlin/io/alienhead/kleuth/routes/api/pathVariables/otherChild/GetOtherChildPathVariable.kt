package io.alienhead.kleuth.routes.api.pathVariables.otherChild

import io.alienhead.kleuth.annotations.RouteController
import io.alienhead.kleuth.resource.TestResource
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PathVariable

@RouteController
class GetOtherChildPathVariable {
  fun handler(@PathVariable testId: String, @PathVariable childId: String): ResponseEntity<TestResource> {
    return ResponseEntity.ok(TestResource("testId: $testId, childId: $childId"))
  }
}
