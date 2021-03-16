package io.alienhead.kleuth.everything.api.test

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
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@SpringBootTest
@ActiveProfiles("everything")
@AutoConfigureMockMvc
class EverythingTests(mvc: MockMvc) : DescribeSpec() {
  override fun listeners(): List<TestListener> {
    return listOf(SpringListener)
  }

  init {
    describe("should map all styles of route handling") {
      describe("/test/multiple") {
        it("should map get request to handler") {
          mvc.perform(
            get("/test/multiple")
              .contentType(MediaType.APPLICATION_JSON)
          )
            .andExpect(status().isOk)
            .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.member", `is`("test")))
        }

        it("should map post request to handler") {
          mvc.perform(
            post("/test/multiple")
              .contentType(MediaType.APPLICATION_JSON)
              .content(ObjectMapper().writeAsString(TestResource("test")))
          )
            .andExpect(status().isOk)
            .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.member", `is`("test")))
        }
      }

      describe("/test/variable") {
        it("should map request to handler") {
          mvc.perform(
            get("/test/variable/{testId}", "1234")
              .contentType(MediaType.APPLICATION_JSON)
          )
            .andExpect(status().isOk)
            .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.member", `is`("testId: 1234")))
        }
      }

      describe("/test/tree") {
        it("should map request to handler") {
          mvc.perform(
            get("/test/tree/{testId}", "1234")
              .contentType(MediaType.APPLICATION_JSON)
          )
            .andExpect(status().isOk)
            .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.member", `is`("testId: 1234")))
        }

        it("should map request to handler with two path variables") {
          mvc.perform(
            get("/test/tree/{testId}/child/{childId}", "1234", "4321")
              .contentType(MediaType.APPLICATION_JSON)
          )
            .andExpect(status().isOk)
            .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.member", `is`("testId: 1234, childId: 4321")))
        }
      }

      describe("/test/separate") {
        it("should map get request to handler") {
          mvc.perform(
            get("/test/separate/{testId}", "1234")
              .contentType(MediaType.APPLICATION_JSON)
          )
            .andExpect(status().isOk)
            .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.member", `is`("testId: 1234")))
        }

        it("should map post request to handler") {
          mvc.perform(
            post("/test/separate/{testId}", "1234")
              .contentType(MediaType.APPLICATION_JSON)
              .content(ObjectMapper().writeAsString(TestResource("test")))
          )
            .andExpect(status().isOk)
            .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.member", `is`("testId: 1234, body member: test")))
        }
      }

      describe("/test/single") {
        it("should map request to handler") {
          mvc.perform(
            get("/test/single")
              .contentType(MediaType.APPLICATION_JSON)
          )
            .andExpect(status().isOk)
            .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.member", `is`("test")))
        }
      }
    }
  }
}
