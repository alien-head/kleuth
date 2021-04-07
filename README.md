# Kleuth

[![Build](https://github.com/alien-head/kleuth/actions/workflows/pr-verify.yml/badge.svg?event=push)](https://github.com/alien-head/kleuth/actions/workflows/pr-verify.yml)
[![ktlint](https://img.shields.io/badge/code%20style-%E2%9D%A4-FF4081.svg)](https://ktlint.github.io/)

kotlin + sleuth = kleuth 🕵️‍♂️ . A lightweight framework for generating Spring REST API routes dynamically.

___

## How it works
Kleuth uses project folders and naming standards to map functions to web requests.

Example:

![Pizza Restauruant REST API](./documentation/assets/pizza_api_structure_only.png)

Classes annotated with `Route` or `RequestMethod` are then used by Kleuth to map the path set in the folder structure to functions.

This is what the `rest/pizzas/GetPizzas.kt` class in the above example looks like:

```kotlin
@Route
class GetPizzas(private val service: PizzaService) {
    fun handler(): ResponseEntity<List<Pizza>> {
        return ResponseEntity.ok(service.getAll())
    }
} 
```

Kleuth also contains a number of helper functions to make route classes even more concise.
The same route can look like this:
```kotlin
@Route
class GetPizzas(private val service: PizzaService) {
    fun handler() = ok { service.getAll() }
} 
```

This is a significant improvement over the standard method of creating Spring `RestController` classes,
which can decrease in readability quickly:
```kotlin
@RestController
class PizzaController(private val service: PizzaService) {
    @GetMapping("/pizzas")
    fun getPizzas(): ResponseEntity<List<Pizza>> {
        return ResponseEntity.ok(service.getAll())
    }

   @PostMapping("/pizzas")
   fun createPizza(@RequestBody body: Pizza): ResponseEntity<Pizza> {
      return ResponseEntity(HttpStatus.CREATED, service.create(body))
   }
   
   // ...

   @GetMapping("/pizzas/{pizzaId}")
   fun getPizzaById(@PathVariable pizzaId: UUID): ResponseEntity<Pizza> {
      return ResponseEntity.ok(service.getById(pizzaId))
   }
}
```

A Kleuth route handler class is also a Spring `RestController`. This means Kleuth functions are completely compatible with Spring RequestMappings. These functions make use of `PathVariable`, `RequestBody`, `RequestParam`, 
and all other possible Spring function annotations and parameters. If for some reason Kleuth does not support a Spring feature, a `RequestMapping` function can co-exist in a Kleuth route handler class.

## Benefits

### Reduce Spring Boilerplate
Kleuth REST API routes are created through the directory structure. 
No more RequestMappings and monolithic Controller classes (Less annotations too!).
This also means less work!

### De-obfuscate Your REST API Structure
The structure of a Kleuth-mapped Spring REST API is clear from the package view. 
Immediately understand the flow of a Kleuth REST API.

### Codify Best Practices and Organization
Kleuth helps enforce clear and concise development practices and project organization.

___

Check out the docs to learn more!
