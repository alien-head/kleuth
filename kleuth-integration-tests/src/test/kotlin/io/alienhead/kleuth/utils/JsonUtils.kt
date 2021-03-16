package io.alienhead.kleuth.utils

import com.fasterxml.jackson.databind.ObjectMapper

inline fun <reified T> ObjectMapper.writeAsString(value: T): String = writeValueAsString(value)
