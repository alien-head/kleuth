package io.alienhead.kleuth

import io.kotest.core.listeners.TestListener
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe
import io.kotest.spring.SpringListener
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles

@SpringBootTest
@ActiveProfiles("test")
class TestApplicationTests : DescribeSpec() {
  override fun listeners(): List<TestListener> {
    return listOf(SpringListener)
  }

  @Value("\${spring.application.name}")
  lateinit var applicationName: String

  init {
    describe("given application") {
      it("loads correct profile") {
        applicationName shouldBe "KleuthTestApplication"
      }
    }
  }
}
