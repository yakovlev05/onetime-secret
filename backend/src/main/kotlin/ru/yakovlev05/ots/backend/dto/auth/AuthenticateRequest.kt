package ru.yakovlev05.ots.backend.dto.auth

import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank

@Schema(description = "Запрос на аутентификацию")
data class AuthenticateRequest(
    @field:Schema(description = "Email пользователя", example = "alexey@pochta.local")
    @field:Email
    @field:NotBlank
    val email: String,

    @field:Schema(description = "Пароль пользователя", example = "Ads%sfd8")
    @field:NotBlank
    val password: String
)