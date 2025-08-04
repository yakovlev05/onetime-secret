package ru.yakovlev05.ots.backend.dto.auth

import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.NotBlank

@Schema(description = "Обновление access токена")
data class RefreshRequest(
    @field:Schema(description = "Refresh token", example = "refresh token")
    @field:NotBlank
    val refreshToken: String
)
