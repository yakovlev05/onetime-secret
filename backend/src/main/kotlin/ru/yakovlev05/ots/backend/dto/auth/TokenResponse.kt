package ru.yakovlev05.ots.backend.dto.auth

import io.swagger.v3.oas.annotations.media.Schema

@Schema(description = "Токены для доступа к защищенным эндпоинтам")
data class TokenResponse(
    @field:Schema(description = "Access токен", example = "access token")
    val accessToken: String,

    @field:Schema(description = "Refresh токен", example = "refresh token")
    val refreshToken: String
)
