package ru.yakovlev05.ots.backend.config

import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Configuration
import ru.yakovlev05.ots.backend.props.JwtProps

@EnableConfigurationProperties(JwtProps::class)
@Configuration
class AppConfig {
}