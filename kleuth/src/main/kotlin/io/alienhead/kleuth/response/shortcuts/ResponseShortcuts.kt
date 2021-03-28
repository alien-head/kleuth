package io.alienhead.kleuth.response.shortcuts

import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity

// 200 Series
fun <T> ok(headers: HttpHeaders = HttpHeaders.EMPTY, action: () -> T) = ResponseEntity(action(), headers, HttpStatus.OK)
fun <T> ok(headers: HttpHeaders = HttpHeaders.EMPTY, body: T) = ResponseEntity(body, headers, HttpStatus.OK)

fun <T> created(headers: HttpHeaders = HttpHeaders.EMPTY, action: () -> T) = ResponseEntity(action(), headers, HttpStatus.CREATED)
fun <T> created(headers: HttpHeaders = HttpHeaders.EMPTY, body: T) = ResponseEntity(body, headers, HttpStatus.CREATED)

fun <T> accepted(headers: HttpHeaders = HttpHeaders.EMPTY, action: () -> T) = ResponseEntity(action(), headers, HttpStatus.ACCEPTED)
fun <T> accepted(headers: HttpHeaders = HttpHeaders.EMPTY, body: T) = ResponseEntity(body, headers, HttpStatus.ACCEPTED)

fun `no content`(headers: HttpHeaders = HttpHeaders.EMPTY, action: () -> Unit): ResponseEntity<Unit> {
  action()
  return ResponseEntity<Unit>(headers, HttpStatus.NO_CONTENT)
}

fun <T> `partial content`(headers: HttpHeaders = HttpHeaders.EMPTY, action: () -> T) =
  ResponseEntity(action(), headers, HttpStatus.PARTIAL_CONTENT)

fun <T> `partial content`(headers: HttpHeaders = HttpHeaders.EMPTY, body: T) =
  ResponseEntity(body, headers, HttpStatus.PARTIAL_CONTENT)

// 300 Series
fun `multiple choices`(headers: HttpHeaders = HttpHeaders.EMPTY, action: () -> Unit): ResponseEntity<Unit> {
  action()
  return ResponseEntity<Unit>(headers, HttpStatus.NO_CONTENT)
}

fun `moved permanently`(headers: HttpHeaders = HttpHeaders.EMPTY, action: () -> Unit): ResponseEntity<Unit> {
  action()
  return ResponseEntity<Unit>(headers, HttpStatus.NO_CONTENT)
}

fun `found`(headers: HttpHeaders = HttpHeaders.EMPTY, action: () -> Unit): ResponseEntity<Unit> {
  action()
  return ResponseEntity<Unit>(headers, HttpStatus.NO_CONTENT)
}

fun `see other`(headers: HttpHeaders = HttpHeaders.EMPTY, action: () -> Unit): ResponseEntity<Unit> {
  action()
  return ResponseEntity<Unit>(headers, HttpStatus.NO_CONTENT)
}
