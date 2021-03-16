package io.alienhead.kleuth.singleHandler.api.test

import io.alienhead.kleuth.annotations.RouteController
import io.alienhead.kleuth.resource.TestResource
import org.springframework.http.ResponseEntity

@RouteController
class GetTestController {
  fun handler(): ResponseEntity<TestResource> {
    return ResponseEntity.ok(TestResource("test"))
  }
}
