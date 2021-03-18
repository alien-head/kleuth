package io.alienhead.kleuth.pathVariables.api.test

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
@ActiveProfiles("pathVariables")
@AutoConfigureMockMvc
class PathVariablesTests(mvc: MockMvc) : DescribeSpec() {
  override fun listeners(): List<TestListener> {
    return listOf(SpringListener)
  }

  init {
    describe("given route and handler") {
      it("should map request to handler") {
        mvc.perform(
          get("/test/{testId}", "1234")
            .contentType(MediaType.APPLICATION_JSON)
        )
          .andExpect(status().isOk)
          .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
          .andExpect(jsonPath("$.member", `is`("testId: 1234")))
      }

      it("should map request to handler with two path variables") {
        mvc.perform(
          get("/test/{testId}/child/{childId}", "1234", "4321")
            .contentType(MediaType.APPLICATION_JSON)
        )
          .andExpect(status().isOk)
          .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
          .andExpect(jsonPath("$.member", `is`("testId: 1234, childId: 4321")))
      }

      it("should map request to other child path with two path variables") {
        mvc.perform(
          get("/test/{testId}/other-child/{childId}", "1234", "4321")
            .contentType(MediaType.APPLICATION_JSON)
        )
          .andExpect(status().isOk)
          .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
          .andExpect(jsonPath("$.member", `is`("testId: 1234, childId: 4321")))
      }
    }
  }
}
