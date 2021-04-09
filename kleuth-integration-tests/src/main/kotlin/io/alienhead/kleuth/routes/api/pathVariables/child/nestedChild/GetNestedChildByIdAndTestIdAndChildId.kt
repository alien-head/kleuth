package io.alienhead.kleuth.routes.api.pathVariables.child.nestedChild

import io.alienhead.kleuth.annotations.Route
import io.alienhead.kleuth.resource.TestResource
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PathVariable

@Route
class GetNestedChildByIdAndTestIdAndChildId {
  fun handler(@PathVariable testId: String, @PathVariable childId: String, @PathVariable nestedChildId: String): ResponseEntity<TestResource> {
    return ResponseEntity.ok(TestResource("testId: $testId, childId: $childId, nestedChildId: $nestedChildId"))
  }
}
