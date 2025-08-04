package ru.yakovlev05.ots.backend.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.AuthenticationEntryPoint
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import ru.yakovlev05.ots.backend.filter.JwtFilter

@Configuration
class SecurityConfig {

    private val public = arrayOf(
        "/swagger-ui/**", "/swagger-resources/*", "/v3/api-docs/**",
        "/api/v1/auth/**",
    )

    @Bean
    fun securityFilterChain(
        http: HttpSecurity, jwtFilter: JwtFilter,
        authenticationEntryPoint: AuthenticationEntryPoint
    ): SecurityFilterChain {
        return http
            .csrf { it.disable() }
            .sessionManagement { it.sessionCreationPolicy(SessionCreationPolicy.STATELESS) }
            .exceptionHandling {
                it
                    .authenticationEntryPoint(authenticationEntryPoint)
            }
            .authorizeHttpRequests {
                it
                    .requestMatchers(*public).permitAll()
                    .anyRequest().authenticated()
            }
            .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter::class.java)
            .build()
    }

    @Bean
    fun passwordEncoder(): PasswordEncoder = BCryptPasswordEncoder()

    @Bean
    fun userDetailsService(): UserDetailsService = UserDetailsService { null }
}
