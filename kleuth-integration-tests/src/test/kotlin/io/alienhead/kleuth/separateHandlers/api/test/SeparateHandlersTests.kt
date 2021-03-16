package io.alienhead.kleuth.separateHandlers.api.test

import com.fasterxml.jackson.databind.ObjectMapper
import io.alienhead.kleuth.resource.TestResource
import io.alienhead.kleuth.utils.writeAsString
import io.kotest.core.listeners.TestListener
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.spring.SpringListener
import org.hamcrest.CoreMatchers.`is`
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@SpringBootTest
@ActiveProfiles("separateHandlers")
@AutoConfigureMockMvc
class SeparateHandlersTests(mvc: MockMvc) : DescribeSpec() {
  override fun listeners(): List<TestListener> {
    return listOf(SpringListener)
  }

  init {
    describe("given route and handler") {
      it("should map get request to handler") {
        mvc.perform(
          get("/test/{testId}", "1234")
            .contentType(MediaType.APPLICATION_JSON)
        )
          .andExpect(status().isOk)
          .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
          .andExpect(jsonPath("$.member", `is`("testId: 1234")))
      }

      it("should map post request to handler") {
        mvc.perform(
          MockMvcRequestBuilders.post("/test/{testId}", "1234")
            .contentType(MediaType.APPLICATION_JSON)
            .content(ObjectMapper().writeAsString(TestResource("test")))
        )
          .andExpect(status().isOk)
          .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
          .andExpect(jsonPath("$.member", `is`("testId: 1234, body member: test")))
      }
    }
  }
}
