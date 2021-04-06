---
title: Content Type
slug: /framework/content-type
---

Kleuth defaults the `produces` and `consumes` attributes of a request mapping to JSON. If you need to override this behavior, say, to consume a CSV file,
the request method annotations (`Get`, `Post`, etc.) provide the option to set custom `produces` and `consumes` attributes.

This is just like what needs to be done on a Spring `RequestMapping` function.

```kotlin
@Post(consumes = "text/csv")
fun postCSV( /* ... */ ) {
    // ...
}
```
