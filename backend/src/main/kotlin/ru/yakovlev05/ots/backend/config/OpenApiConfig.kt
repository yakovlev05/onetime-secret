package ru.yakovlev05.ots.backend.config

import io.swagger.v3.oas.annotations.OpenAPIDefinition
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType
import io.swagger.v3.oas.annotations.info.Info
import io.swagger.v3.oas.annotations.security.SecurityScheme
import io.swagger.v3.oas.annotations.servers.Server

//@OpenAPIDefinition()
//@OpenAPIDefinition(
//    info = Info(title = "Flash", description = "API мессенджера Flash"),
//    servers = [Server(url = "/")]
//) // Теперь в cookie
@OpenAPIDefinition(
    info = Info(title = "Onetime-secret", description = "API onetime-secret"),
    servers = [Server(url = "/", description = "current")]
)
@SecurityScheme(name = "JWT", type = SecuritySchemeType.HTTP, bearerFormat = "JWT", scheme = "bearer")
class OpenApiConfig {

}