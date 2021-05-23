package io.alienhead.kleuth.routes.api.content

import io.alienhead.kleuth.annotations.request.Content
import io.alienhead.kleuth.annotations.routing.Route
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RequestBody

@Route
class GetConsumes {

  @Content(consumes = "text/plain")
  fun handler(@RequestBody body: String): ResponseEntity<String> = ResponseEntity.ok(body)
}
