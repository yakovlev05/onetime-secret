package ru.yakovlev05.ots.backend.dto.auth

import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Size

@Schema(description = "Регистрация пользователя")
data class RegisterRequest(
    @field:Schema(description = "Имя пользователя", example = "Алексей")
    @field:NotBlank
    val name: String,

    @field:Schema(description = "Email пользователя", example = "alexey@pochta.local")
    @field:Email
    @field:NotBlank
    val email: String,

    @field:Schema(description = "Пароль пользователя", example = "Ads%sfd8")
    @field:Size(min = 8)
    val password: String
)