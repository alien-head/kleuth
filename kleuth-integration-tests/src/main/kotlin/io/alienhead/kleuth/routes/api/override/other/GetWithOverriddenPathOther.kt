package io.alienhead.kleuth.routes.api.override.other

import io.alienhead.kleuth.annotations.RequestMethod
import io.alienhead.kleuth.resource.TestResource
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@RequestMethod("/overridden-path/{testId}/other")
@RestController
class GetWithOverriddenPathOther {
  fun handler(@PathVariable testId: String): ResponseEntity<TestResource> {
    return ResponseEntity.ok(TestResource("testId: $testId"))
  }
}
