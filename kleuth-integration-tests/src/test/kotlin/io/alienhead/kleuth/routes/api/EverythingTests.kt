package io.alienhead.kleuth.routes.api

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
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@SpringBootTest
@ActiveProfiles("routes")
@AutoConfigureMockMvc
class EverythingTests(mvc: MockMvc) : DescribeSpec() {
  override fun listeners(): List<TestListener> {
    return listOf(SpringListener)
  }

  init {
    describe("should map all styles of route handling") {
      describe("/multiple") {
        it("should map get request to handler") {
          mvc.perform(
            get("/multiple")
              .contentType(MediaType.APPLICATION_JSON)
          )
            .andExpect(status().isOk)
            .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.member", `is`("test")))
        }

        it("should map post request to handler") {
          mvc.perform(
            post("/multiple")
              .contentType(MediaType.APPLICATION_JSON)
              .content(ObjectMapper().writeAsString(TestResource("test")))
          )
            .andExpect(status().isOk)
            .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.member", `is`("test")))
        }
      }

      describe("/path-variables") {
        it("should map request to handler") {
          mvc.perform(
            get("/path-variables/{testId}", "1234")
              .contentType(MediaType.APPLICATION_JSON)
          )
            .andExpect(status().isOk)
            .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.member", `is`("testId: 1234")))
        }

        it("should map request to nested handler") {
          mvc.perform(
            get("/path-variables/{testId}/child", "1234")
              .contentType(MediaType.APPLICATION_JSON)
          )
            .andExpect(status().isOk)
            .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.member", `is`("Child with testId: 1234")))
        }

        it("should map request to handler with two path variables") {
          mvc.perform(
            get("/path-variables/{testId}/child/{childId}", "1234", "4321")
              .contentType(MediaType.APPLICATION_JSON)
          )
            .andExpect(status().isOk)
            .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.member", `is`("testId: 1234, childId: 4321")))
        }
      }

      describe("/separate") {
        it("should map get request to handler") {
          mvc.perform(
            get("/separate/{testId}", "1234")
              .contentType(MediaType.APPLICATION_JSON)
          )
            .andExpect(status().isOk)
            .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.member", `is`("testId: 1234")))
        }

        it("should map post request to handler") {
          mvc.perform(
            post("/separate/{testId}", "1234")
              .contentType(MediaType.APPLICATION_JSON)
              .content(ObjectMapper().writeAsString(TestResource("test")))
          )
            .andExpect(status().isOk)
            .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.member", `is`("testId: 1234, body member: test")))
        }
      }

      describe("/single") {
        it("should map request to handler") {
          mvc.perform(
            get("/single")
              .contentType(MediaType.APPLICATION_JSON)
          )
            .andExpect(status().isOk)
            .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.member", `is`("test")))
        }
      }

      describe("/override") {
        it("should map get request to handler when path is overridden in @Route") {
          mvc.perform(
            get("/overridden-path/{testId}", "1234")
              .contentType(MediaType.APPLICATION_JSON)
          )
            .andExpect(status().isOk)
            .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.member", `is`("testId: 1234")))
        }

        it("should map get request to handler when path is overridden in @RequestMethod") {
          mvc.perform(
            get("/overridden-path/{testId}/other", "1234")
              .contentType(MediaType.APPLICATION_JSON)
          )
            .andExpect(status().isOk)
            .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.member", `is`("testId: 1234")))
        }
      }

      describe("/produces") {
        it("should map get request to handler and override default produces") {
          mvc.perform(
            get("/produces/{testId}", "1234")
              .contentType(MediaType.APPLICATION_JSON)
          )
            .andExpect(status().isOk)
            .andExpect(content().contentTypeCompatibleWith(MediaType.parseMediaType("text/csv")))
        }
      }

      describe("/consumes") {
        it("should map get request to handler and override default produces") {
          mvc.perform(
            post("/consumes")
              .contentType(MediaType.TEXT_PLAIN)
              .content("1234")
          )
            .andExpect(status().isOk)
            .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
            .andExpect(content().string("1234"))
        }
      }

      describe("/delete") {
        it("should map delete request to named request") {
          mvc.perform(
            delete("/delete/{resourceId}", "1234")
              .contentType(MediaType.APPLICATION_JSON)
          )
            .andExpect(status().isOk)
            .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$", `is`("Deleted 1234")))
        }
      }
    }
  }
}
