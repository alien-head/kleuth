package io.alienhead.kleuth.everything.api.test.separate

import io.alienhead.kleuth.annotations.RouteController
import io.alienhead.kleuth.resource.TestResource
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestBody

@RouteController
class PostEverythingSeparateHandler {
  fun handler(@PathVariable testId: String, @RequestBody body: TestResource): ResponseEntity<TestResource> {
    return ResponseEntity.ok(TestResource("testId: $testId, body member: ${body.member}"))
  }
}
