package io.alienhead.kleuth.routes.api.pathVariables.child.nestedChild

import io.alienhead.kleuth.annotations.routing.Route
import io.alienhead.kleuth.resource.TestResource
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PathVariable

@Route
class GetNestedChild {
  fun handler(@PathVariable testId: String, @PathVariable childId: String): ResponseEntity<TestResource> {
    return ResponseEntity.ok(TestResource("Nested child: testId: $testId, childId: $childId"))
  }
}
