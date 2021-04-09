package io.alienhead.kleuth.routes.api.pathVariables.child.nestedChild

import io.alienhead.kleuth.annotations.Route
import io.alienhead.kleuth.resource.TestResource
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestBody

@Route
class PostNestedChild {
  fun handler(
    @PathVariable testId: String,
    @PathVariable childId: String,
    @PathVariable nestedChildId: String,
    @RequestBody body: TestResource
  ): ResponseEntity<TestResource> {
    return ResponseEntity.ok(TestResource("$testId, $childId, $nestedChildId, ${body.member}"))
  }
}
