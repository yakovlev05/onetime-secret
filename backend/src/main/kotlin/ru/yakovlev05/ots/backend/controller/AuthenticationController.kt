package ru.yakovlev05.ots.backend.controller

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import ru.yakovlev05.ots.backend.dto.auth.AuthenticateRequest
import ru.yakovlev05.ots.backend.dto.auth.TokenResponse
import ru.yakovlev05.ots.backend.dto.auth.RefreshRequest
import ru.yakovlev05.ots.backend.dto.auth.RegisterRequest
import ru.yakovlev05.ots.backend.service.AuthenticationService

@Tag(name = "Authentication", description = "API аутентификации")
@RestController
@RequestMapping("/api/v1/auth")
class AuthenticationController(
    val authenticationService: AuthenticationService
) {

    @Operation(summary = "Аутентификация пользователя (вход)")
//    @ApiResponses
    @PostMapping("/authenticate")
    fun authenticate(@RequestBody @Valid request: AuthenticateRequest): TokenResponse =
        authenticationService.authenticate(request)

    @Operation(summary = "Регистрация пользователя")
    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    fun register(@RequestBody @Valid request: RegisterRequest) = authenticationService.register(request)

    @Operation(summary = "Обновить токены")
    @PostMapping("/refresh")
    fun refresh(@RequestBody @Valid request: RefreshRequest): TokenResponse = authenticationService.refresh(request)
}