---
title: Compatibility Notes
slug: /compatibility-notes
---

### Function-level Spring Annotations
Kleuth request method handler functions work exactly like Spring `RequestMapping` functions. 
However, function-level Spring annotations like `ResponseBody` or `PreAuthorize` do not work on Kleuth handler functions. 
Luckily, Spring usually provides multiple ways to get the same job done.

:::tip
Ant matchers can handle the same behavior of `PreAuthorize`, for one example. 
If you are following the Kleuth conventions and your route handlers are 1-to-1 with route handler classes, 
then this is the correct way to handle Pre-Authorization anyway.
:::

Since handler classes retain the `RestController` annotation, `RequestMapping` functions can even appear inside handler classes.
If for some reason Kleuth does not support the Spring feature you need, you can simply create a standard request mapping function.

### Testing

Kleuth is completely compatible with Spring testing features like `MockMVC`.

### Removing Kleuth

Removing Kleuth not a time stink--just replace the Kleuth annotations with `RestController` and add `RequestMapping` annotations to the route handler function(s).
