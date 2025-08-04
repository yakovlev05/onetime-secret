package ru.yakovlev05.ots.backend.service

import ru.yakovlev05.ots.backend.dto.auth.AuthenticateRequest
import ru.yakovlev05.ots.backend.dto.auth.RefreshRequest
import ru.yakovlev05.ots.backend.dto.auth.TokenResponse
import ru.yakovlev05.ots.backend.dto.auth.RegisterRequest

interface AuthenticationService {
    fun authenticate(request: AuthenticateRequest): TokenResponse
    fun register(request: RegisterRequest)
    fun refresh(request: RefreshRequest): TokenResponse
}