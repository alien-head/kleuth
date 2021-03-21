package io.alienhead.kleuth.routes.api.single

import io.alienhead.kleuth.annotations.RouteController
import io.alienhead.kleuth.resource.TestResource
import org.springframework.http.ResponseEntity

@RouteController
class GetSingleHandler {
  fun handler(): ResponseEntity<TestResource> {
    return ResponseEntity.ok(TestResource("test"))
  }
}
