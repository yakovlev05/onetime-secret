package ru.yakovlev05.ots.backend.dto.exception

data class ValidationError(
    val field: String,
    val value: Any?,
    val message: String?,
)