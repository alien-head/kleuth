package io.alienhead.kleuth.routes.api.produces

import io.alienhead.kleuth.annotations.request.Get
import io.alienhead.kleuth.annotations.routing.Route
import org.apache.commons.csv.CSVFormat
import org.apache.commons.csv.CSVPrinter
import org.springframework.core.io.InputStreamResource
import org.springframework.core.io.Resource
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PathVariable
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import java.io.PrintWriter

@Route
class GetProduces {

  @Get(produces = "text/csv")
  fun getCsv(@PathVariable itemId: String): ResponseEntity<Resource> {
    val csvHeader = arrayOf("resourceId")

    // replace this with your data retrieving logic
    val csvBody = ArrayList<List<String>>()
    csvBody.add(listOf("123"))

    val byteArrayOutputStream =
      ByteArrayOutputStream()
        .use { out ->
          // defining the CSV printer
          CSVPrinter(
            PrintWriter(out),
            // withHeader is optional
            CSVFormat.DEFAULT.withHeader(*csvHeader)
          )
            .use { csvPrinter ->
              // populating the CSV content
              csvBody.forEach { record ->
                csvPrinter.printRecord(record)
              }

              // writing the underlying stream
              csvPrinter.flush()

              ByteArrayInputStream(out.toByteArray())
            }
        }

    val fileInputStream = InputStreamResource(byteArrayOutputStream)

    val csvFileName = "people.csv"

    // setting HTTP headers
    val headers = HttpHeaders()
    headers.set(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=$csvFileName")
    // defining the custom Content-Type
    headers.set(HttpHeaders.CONTENT_TYPE, "text/csv")

    return ResponseEntity(
      fileInputStream,
      headers,
      HttpStatus.OK
    )
  }
}
