package io.alienhead.kleuth.routes.api.delete

import io.alienhead.kleuth.annotations.RouteController
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PathVariable

@RouteController
class ResourceDeleteRoute {
  fun delete(@PathVariable resourceId: String) = ResponseEntity.ok("Deleted $resourceId")
}
