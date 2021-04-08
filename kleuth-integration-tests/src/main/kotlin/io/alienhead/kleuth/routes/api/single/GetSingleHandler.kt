package io.alienhead.kleuth.routes.api.single

import io.alienhead.kleuth.annotations.Route
import io.alienhead.kleuth.resource.TestResource
import org.springframework.http.ResponseEntity

@Route
class GetSingleHandler {
  fun handler(): ResponseEntity<TestResource> {
    return ResponseEntity.ok(TestResource("test"))
  }
}
