---
title: Response Entity Shortcuts
slug: /framework/response-entity-shortcuts
---

Kleuth provides helper functions to reduce `ResponseEntity` boilerplate.

### Examples

#### Http Status 200
Before:
```kotlin
@Route
class GetPizzas(
    private val service: PizzaService
) {
    fun handler(): ResponseEntity<List<Pizza>> {
        return ResponseEntity.ok(service.getAll())
    }
}
```

After:
```kotlin
@Route
class GetPizzas(
    private val service: PizzaService
) {
    fun handler() = ok { service.getAll() }
}

```

#### Http Status 204
Before:
```kotlin
@Route
class PutOrder(
    private val service: OrderService
) {
    fun put(@PathVariable id: UUID, @RequestBody body: Order): ResponseEntity<Unit> {
        val existing = service.findById(id) ?: throw Exception("Not Found")
        service.update(body)
        
        return ResponseEntity(HttpStatus.NO_CONTENT)
    }
}
```

After:
```kotlin
@Route
class PutOrder(
    private val service: OrderService
) {
    fun put(@PathVariable id: UUID, @RequestBody body: Order) = `no content` {
        val existing = service.findById(id) ?: throw Exception("Not Found")
        service.update(body)
    }
}
```

### Function References
The functions can either be called with a body, or with a function reference:

```kotlin
val body = service.get()

return ok(body)
```
is equivalent to:
```kotlin
return ok { service.get() }
```

### Headers

Headers can also be passed in:
```kotlin
@Route
class PostOrder(
    private val service: OrderService
) {
    fun post(@PathVariable id: UUID, @RequestBody body: Order) =
        if (service.findById(id) == null) {
            val headers = HttpHeaders()
            
            headers.add("existing-id", id.toString())
            
            `no content`(headers)
        } else {
            created {
                val existing = service.findById(id) ?: throw Exception("Not Found")
                service.update(body)
            }
        }
}
```

### Full List
| Http Status | Function Name |
|-------------|-------------|
| 200 |`ok`|
| 201 |`created`|
| 202 |`accepted`|
| 204 |`no content`|
| 206 |`partial content`|
| 300 |`multiple choices`|
| 301 |`moved permanently`|
| 302 |`found`|
| 303 |`see other`|
