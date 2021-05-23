package io.alienhead.kleuth.routes.api.multiple

import io.alienhead.kleuth.annotations.request.Post
import io.alienhead.kleuth.annotations.routing.Route
import io.alienhead.kleuth.resource.TestResource
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RequestBody

@Route
class GetMultipleHandlers {
  fun handler(): ResponseEntity<TestResource> {
    return ResponseEntity.ok(TestResource("test"))
  }

  @Post
  fun postHandler(@RequestBody body: TestResource): ResponseEntity<TestResource> {
    return ResponseEntity.ok(body)
  }
}
