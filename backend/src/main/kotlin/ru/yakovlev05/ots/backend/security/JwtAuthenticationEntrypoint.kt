package ru.yakovlev05.ots.backend.security

import com.fasterxml.jackson.databind.ObjectMapper
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.security.core.AuthenticationException
import org.springframework.security.web.AuthenticationEntryPoint
import org.springframework.stereotype.Component
import ru.yakovlev05.ots.backend.dto.exception.ErrorResponse

@Component
class JwtAuthenticationEntrypoint(private val objectMapper: ObjectMapper) : AuthenticationEntryPoint {
    override fun commence(
        request: HttpServletRequest,
        response: HttpServletResponse,
        authException: AuthenticationException
    ) {
        val error = ErrorResponse(
            status = HttpStatus.UNAUTHORIZED.value(),
            message = "Unauthorized",
            path = request.requestURI
        )
        val body = objectMapper.writeValueAsString(error)

        response.status = HttpStatus.UNAUTHORIZED.value()
        response.contentType = MediaType.APPLICATION_JSON_VALUE
        response.writer.write(body)
    }
}