package io.alienhead.kleuth.response.shortcuts

import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity

// 200 Series

/**
 * Builds a [ResponseEntity] with the Http Status of 200
 *
 * @param headers an optional list of [HttpHeaders] to include on the response
 * @param action the function to execute that returns the body for the response
 */
fun <T> ok(headers: HttpHeaders = HttpHeaders.EMPTY, action: () -> T) = ResponseEntity(action(), headers, HttpStatus.OK)

/**
 * Builds a [ResponseEntity] with the Http Status of 200
 *
 * @param headers an optional list of [HttpHeaders] to include on the response
 * @param body the body for the response
 */
fun <T> ok(headers: HttpHeaders = HttpHeaders.EMPTY, body: T) = ResponseEntity(body, headers, HttpStatus.OK)

/**
 * Builds a [ResponseEntity] with the Http Status of 201
 *
 * @param headers an optional list of [HttpHeaders] to include on the response
 * @param action the function to execute that returns the body for the response
 */
fun <T> created(headers: HttpHeaders = HttpHeaders.EMPTY, action: () -> T) = ResponseEntity(action(), headers, HttpStatus.CREATED)

/**
 * Builds a [ResponseEntity] with the Http Status of 201
 *
 * @param headers an optional list of [HttpHeaders] to include on the response
 * @param body the body for the response
 */
fun <T> created(headers: HttpHeaders = HttpHeaders.EMPTY, body: T) = ResponseEntity(body, headers, HttpStatus.CREATED)

/**
 * Builds a [ResponseEntity] with the Http Status of 202
 *
 * @param headers an optional list of [HttpHeaders] to include on the response
 * @param action the function to execute that returns the body for the response
 */
fun <T> accepted(headers: HttpHeaders = HttpHeaders.EMPTY, action: () -> T) = ResponseEntity(action(), headers, HttpStatus.ACCEPTED)

/**
 * Builds a [ResponseEntity] with the Http Status of 202
 *
 * @param headers an optional list of [HttpHeaders] to include on the response
 * @param body the body for the response
 */
fun <T> accepted(headers: HttpHeaders = HttpHeaders.EMPTY, body: T) = ResponseEntity(body, headers, HttpStatus.ACCEPTED)

/**
 * Builds a [ResponseEntity] with the Http Status of 204
 *
 * @param headers an optional list of [HttpHeaders] to include on the response
 * @param action the function to execute.
 */
fun `no content`(headers: HttpHeaders = HttpHeaders.EMPTY, action: () -> Unit): ResponseEntity<Unit> {
  action()
  return ResponseEntity<Unit>(headers, HttpStatus.NO_CONTENT)
}

/**
 * Builds a [ResponseEntity] with the Http Status of 204
 *
 * @param headers an optional list of [HttpHeaders] to include on the response
 */
fun `no content`(headers: HttpHeaders = HttpHeaders.EMPTY): ResponseEntity<Unit> {
  return ResponseEntity<Unit>(headers, HttpStatus.NO_CONTENT)
}

/**
 * Builds a [ResponseEntity] with the Http Status of 206
 *
 * @param headers an optional list of [HttpHeaders] to include on the response
 * @param action the function to execute that returns the body for the response
 */
fun <T> `partial content`(headers: HttpHeaders = HttpHeaders.EMPTY, action: () -> T) =
  ResponseEntity(action(), headers, HttpStatus.PARTIAL_CONTENT)

/**
 * Builds a [ResponseEntity] with the Http Status of 206
 *
 * @param headers an optional list of [HttpHeaders] to include on the response
 * @param body the body for the response
 */
fun <T> `partial content`(headers: HttpHeaders = HttpHeaders.EMPTY, body: T) =
  ResponseEntity(body, headers, HttpStatus.PARTIAL_CONTENT)

// 300 Series

/**
 * Builds a [ResponseEntity] with the Http Status of 300
 *
 * @param headers an optional list of [HttpHeaders] to include on the response
 * @param action the function to execute.
 */
fun `multiple choices`(headers: HttpHeaders = HttpHeaders.EMPTY, action: () -> Unit): ResponseEntity<Unit> {
  action()
  return ResponseEntity<Unit>(headers, HttpStatus.NO_CONTENT)
}

/**
 * Builds a [ResponseEntity] with the Http Status of 301
 *
 * @param headers an optional list of [HttpHeaders] to include on the response
 * @param action the function to execute.
 */
fun `moved permanently`(headers: HttpHeaders = HttpHeaders.EMPTY, action: () -> Unit): ResponseEntity<Unit> {
  action()
  return ResponseEntity<Unit>(headers, HttpStatus.NO_CONTENT)
}

/**
 * Builds a [ResponseEntity] with the Http Status of 302
 *
 * @param headers an optional list of [HttpHeaders] to include on the response
 * @param action the function to execute.
 */
fun `found`(headers: HttpHeaders = HttpHeaders.EMPTY, action: () -> Unit): ResponseEntity<Unit> {
  action()
  return ResponseEntity<Unit>(headers, HttpStatus.NO_CONTENT)
}

/**
 * Builds a [ResponseEntity] with the Http Status of 303
 *
 * @param headers an optional list of [HttpHeaders] to include on the response
 * @param action the function to execute.
 */
fun `see other`(headers: HttpHeaders = HttpHeaders.EMPTY, action: () -> Unit): ResponseEntity<Unit> {
  action()
  return ResponseEntity<Unit>(headers, HttpStatus.NO_CONTENT)
}
