package ru.yakovlev05.ots.backend.exception.handler

import jakarta.servlet.http.HttpServletRequest
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import ru.yakovlev05.ots.backend.dto.exception.ErrorResponse
import ru.yakovlev05.ots.backend.dto.exception.ValidationError
import ru.yakovlev05.ots.backend.exception.BusinessException

@RestControllerAdvice
class GlobalExceptionHandler {

    private val log = LoggerFactory.getLogger(GlobalExceptionHandler::class.java)

    @ExceptionHandler
    fun handleException(ex: Exception, request: HttpServletRequest): ResponseEntity<ErrorResponse> {
        log.error(ex.message, ex)
        return buildResponse(
            HttpStatus.INTERNAL_SERVER_ERROR,
            "Internal Server Error",
            request.requestURI
        )
    }

    @ExceptionHandler
    fun handleBusinessException(ex: BusinessException, request: HttpServletRequest): ResponseEntity<ErrorResponse> {
        return buildResponse(
            ex.status,
            ex.message,
            request.requestURI
        )
    }

    @ExceptionHandler
    fun handleMethodArgumentNotValidException(
        ex: MethodArgumentNotValidException,
        request: HttpServletRequest
    ): ResponseEntity<ErrorResponse> {
        val validationErrors = ex.fieldErrors.map {
            ValidationError(
                field = it.field,
                value = it.rejectedValue,
                message = it.defaultMessage
            )
        }

        return buildResponse(
            HttpStatus.BAD_REQUEST,
            "Ошибка валидации",
            request.requestURI,
            validationErrors
        )
    }

    private fun buildResponse(
        status: HttpStatus,
        message: String?,
        path: String,
        validationErrors: List<ValidationError>? = null
    ): ResponseEntity<ErrorResponse> {
        val body = ErrorResponse(
            status = status.value(),
            message = message,
            path = path,
            validationErrors = validationErrors
        )

        return ResponseEntity.status(status).body(body)
    }
}