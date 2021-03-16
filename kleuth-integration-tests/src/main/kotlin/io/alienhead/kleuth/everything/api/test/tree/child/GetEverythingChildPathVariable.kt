package io.alienhead.kleuth.everything.api.test.tree.child

import io.alienhead.kleuth.annotations.RouteController
import io.alienhead.kleuth.resource.TestResource
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PathVariable

@RouteController
class GetEverythingChildPathVariable {
  fun handler(@PathVariable testId: String, @PathVariable childId: String): ResponseEntity<TestResource> {
    return ResponseEntity.ok(TestResource("testId: $testId, childId: $childId"))
  }
}
