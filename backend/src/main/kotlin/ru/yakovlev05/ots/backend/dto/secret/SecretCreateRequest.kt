package ru.yakovlev05.ots.backend.dto.secret

import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.Min
import jakarta.validation.constraints.NotNull

@Schema(description = "Запрос на создание секрета")
data class SecretCreateRequest(
    @field:Schema(description = "Значение секрета", example = "user:pass")
    @field:NotNull
    val value: String,

    @field:Schema(description = "Время жизни секрета в секундах", example = "86400")
    @field:Min(0)
    val lifetime: Long,

    @field:Schema(description = "Пароль на секрет. (опционально)", example = "password123")
    val password: String?,

    @field:Schema(description = "Email получателя для уведомления. (опционально)", example = "alexey@mail.local")
    val recipientEmail: String?,

    @field:Schema(description = "Email создателя для уведомлений. (опционально)", example = "vladimir@mail.local")
    val creatorEmail: String?,

    @field:Schema(
        description = "Максимально число просмотров. (По умолчанию 1)",
        example = "3",
        requiredMode = Schema.RequiredMode.NOT_REQUIRED
    )
    @field:Min(0)
    val maxViews: Int = 1,
)
