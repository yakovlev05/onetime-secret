package ru.yakovlev05.ots.backend.controller

import io.swagger.v3.oas.annotations.security.SecurityRequirement
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.validation.Valid
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import ru.yakovlev05.ots.backend.dto.secret.SecretCreateRequest
import ru.yakovlev05.ots.backend.dto.secret.SecretCreateResponse
import ru.yakovlev05.ots.backend.entity.User
import ru.yakovlev05.ots.backend.service.SecretService

@SecurityRequirement(name = "JWT")
@Tag(name = "Secret", description = "API для управления секретами")
@RestController
@RequestMapping("/api/v1/secrets")
class SecretController(private val secretService: SecretService) {

    @PostMapping
    fun create(
        @RequestBody @Valid request: SecretCreateRequest,
        @AuthenticationPrincipal principal: User?
    ): SecretCreateResponse = secretService.create(request, principal)
}