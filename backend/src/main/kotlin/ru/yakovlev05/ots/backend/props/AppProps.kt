package ru.yakovlev05.ots.backend.props

import jakarta.validation.constraints.NotBlank
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.validation.annotation.Validated

@Validated
@ConfigurationProperties("app")
data class AppProps(
    @field:NotBlank
    var hostUrl: String = ""
)