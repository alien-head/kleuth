package io.alienhead.kleuth.rotueWithHyphen.api.testHyphen

import io.kotest.core.listeners.TestListener
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.spring.SpringListener
import org.hamcrest.CoreMatchers.`is`
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@SpringBootTest
@ActiveProfiles("camelCase")
@AutoConfigureMockMvc
class PackageWithHyphensTests(mvc: MockMvc) : DescribeSpec() {
  override fun listeners(): List<TestListener> {
    return listOf(SpringListener)
  }

  init {
    describe("given package has camel case") {
      it("should map request to handler and change camel case to kebab case") {
        mvc.perform(
          get("/camel-case/{testId}", "1234")
            .contentType(MediaType.APPLICATION_JSON)
        )
          .andExpect(status().isOk)
          .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
          .andExpect(jsonPath("$.member", `is`("testId: 1234")))
      }

      it("should map child routes with camel case") {
        mvc.perform(
          get("/camel-case/{testId}/child-with-camel-case/{childId}", "1234", "4321")
            .contentType(MediaType.APPLICATION_JSON)
        )
          .andExpect(status().isOk)
          .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
          .andExpect(jsonPath("$.member", `is`("testId: 1234, childId: 4321")))
      }
    }
  }
}
