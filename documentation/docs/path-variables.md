---
title: Path Variables
slug: /framework/path-variables
---

Like `RequestBody`, Kleuth uses Spring's `PathVariable` annotation to include path variables in the url path.

Mark function parameters as path variables the same as if creating the request mapping with the Spring `RequestMapping` annotation:
```kotlin title="/pizzas/GetPizzaById.kt"
@Route
class GetPizzaById(
    private val service: PizzaService
) {
    fun get(@PathVariable id: UUID): ResponseEntity<Pizza> {
        val pizza = service.findById(id) ?: throw Exception("Pizza not found")
        return ResponseEntity.ok(pizza)
    }
}
```

The route will map to `/pizzas/{id}`.

Kleuth will also see if the value/name of the `PathVariable` annotation has been set:
```kotlin title="/pizzas/GetPizzaById.kt"
@Route
class GetPizzaById(
    private val service: PizzaService
) {
    fun get(@PathVariable(name = "pizzaId") id: UUID): ResponseEntity<Pizza> {
        val pizza = service.findById(id) ?: throw Exception("Pizza not found")
        return ResponseEntity.ok(pizza)
    }
}
```

The route will correctly map to `/pizzas/{pizzaId}`.
