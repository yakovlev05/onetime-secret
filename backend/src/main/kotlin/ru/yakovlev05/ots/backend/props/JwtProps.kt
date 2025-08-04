package ru.yakovlev05.ots.backend.props

import jakarta.validation.constraints.Min
import jakarta.validation.constraints.NotBlank
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.validation.annotation.Validated

@Validated
@ConfigurationProperties("app.security.jwt")
data class JwtProps(

    @field:NotBlank
    var secretKey: String = "",

    @field:Min(0)
    var accessTokenValidity: Long = -1,

    @field:Min(0)
    var refreshTokenValidity: Long = -1,
)