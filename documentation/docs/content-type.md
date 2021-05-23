---
title: Content Type
slug: /framework/content-type
---

Kleuth defaults the `produces` and `consumes` attributes of a request mapping to JSON (depending on HTTP method). If you need to override this behavior, say, to consume a CSV file,
there are different options. This mirrors Spring's content setting.

The `Content` annotation provides this functionality for handler and function name routing styles.
```kotlin
@Route
class Csv {
    @Content(consumes = "text/csv")
    fun handler( /* ... */ ) {
        // ...
    }
}
```

The request method annotations (`Get`, `Post`, etc.) provide the option to set custom `produces` and `consumes` attributes.

```kotlin
@Post(consumes = "text/csv")
fun postCSV( /* ... */ ) {
    // ...
}
```
