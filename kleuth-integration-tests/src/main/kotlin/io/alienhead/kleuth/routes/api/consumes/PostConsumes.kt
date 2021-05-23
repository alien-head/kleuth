package io.alienhead.kleuth.routes.api.consumes

import io.alienhead.kleuth.annotations.request.Post
import io.alienhead.kleuth.annotations.routing.Route
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RequestBody

@Route
class PostConsumes {

  @Post(consumes = "text/plain")
  fun postText(@RequestBody body: String) = ResponseEntity.ok(body)
}
