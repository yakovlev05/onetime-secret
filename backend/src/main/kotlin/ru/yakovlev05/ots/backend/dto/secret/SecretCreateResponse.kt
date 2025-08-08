package ru.yakovlev05.ots.backend.dto.secret

import io.swagger.v3.oas.annotations.media.Schema

@Schema(description = "Ответ при создании секрета")
data class SecretCreateResponse(
    @field:Schema(description = "Короткий URL. Может быть null", example = "https://secret.yakovlev05.ru/s/secret-url")
    val shortUrl: String?,

    @field:Schema(
        description = "Стандартный URL. Всегда существует",
        example = "https://secret.yakovlev05.ru/s/secret-url"
    )
    val url: String?
)
