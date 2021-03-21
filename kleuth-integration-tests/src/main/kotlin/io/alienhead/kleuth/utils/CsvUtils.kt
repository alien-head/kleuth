package io.alienhead.kleuth.utils

import com.fasterxml.jackson.databind.ObjectReader
import com.fasterxml.jackson.dataformat.csv.CsvMapper
import io.alienhead.kleuth.resource.TestResource
import java.io.InputStream

class CsvUtils {
  companion object {

    fun readCsvOfResource(stream: InputStream): List<TestResource> {
      val csvMapper = CsvMapper()
      val schema = csvMapper.schemaFor(TestResource::class.java).withColumnReordering(true)
      val reader: ObjectReader = csvMapper.readerFor(TestResource::class.java).with(schema)

      return reader.readValues<TestResource>(stream).readAll()
    }
  }
}
