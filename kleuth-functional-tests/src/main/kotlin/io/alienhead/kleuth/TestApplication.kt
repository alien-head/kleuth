package io.alienhead.kleuth

import io.alienhead.kleuth.config.EnableKleuth
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
@EnableKleuth
class TestApplication

fun main(args: Array<String>) {
  runApplication<TestApplication>(*args)
}