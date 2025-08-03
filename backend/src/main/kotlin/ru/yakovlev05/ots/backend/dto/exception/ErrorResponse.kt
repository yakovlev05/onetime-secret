package ru.yakovlev05.ots.backend.dto.exception

import java.time.Instant

data class ErrorResponse(
    val status: Int,
    val message: String,
    val path: String,
    val timestamp: Instant = Instant.now(),
    val validationErrors: List<ValidationError>? = null
)