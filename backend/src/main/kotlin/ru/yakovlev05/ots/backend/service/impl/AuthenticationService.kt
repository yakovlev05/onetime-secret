package ru.yakovlev05.ots.backend.service.impl

import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import ru.yakovlev05.ots.backend.dto.auth.AuthenticateRequest
import ru.yakovlev05.ots.backend.dto.auth.RefreshRequest
import ru.yakovlev05.ots.backend.dto.auth.RegisterRequest
import ru.yakovlev05.ots.backend.dto.auth.TokenResponse
import ru.yakovlev05.ots.backend.entity.User
import ru.yakovlev05.ots.backend.exception.AuthenticationException
import ru.yakovlev05.ots.backend.exception.EmailAlreadyExistsException
import ru.yakovlev05.ots.backend.exception.EmailNotVerifiedException
import ru.yakovlev05.ots.backend.exception.RefreshTokenInvalid
import ru.yakovlev05.ots.backend.mapper.toUser
import ru.yakovlev05.ots.backend.provider.JwtProvider
import ru.yakovlev05.ots.backend.provider.TokenType
import ru.yakovlev05.ots.backend.service.AuthenticationService
import ru.yakovlev05.ots.backend.service.UserService

@Service
class AuthenticationService(
    private val userService: UserService,
    private val passwordEncoder: PasswordEncoder,
    private val jwtProvider: JwtProvider,
) : AuthenticationService {

    override fun authenticate(request: AuthenticateRequest): TokenResponse {
        val user = tryAuthenticate(request)
        checkEmailVerified(user)

        return buildTokenResponse(user.id.toString())
    }

    override fun register(request: RegisterRequest) {
        checkEmail(request.email)
        val user = request.toUser(passwordEncoder.encode(request.password))
        userService.save(user)
    }

    override fun refresh(request: RefreshRequest): TokenResponse {
        val isValid = jwtProvider.isValid(request.refreshToken, TokenType.REFRESH_TOKEN)
        if (!isValid) throw RefreshTokenInvalid()

        val user = userService.findById(jwtProvider.extractSubject(request.refreshToken).toLong())
        user ?: throw RefreshTokenInvalid()

        return buildTokenResponse(user.id.toString())
    }

    private fun checkEmail(email: String) {
        if (userService.existsByEmail(email)) throw EmailAlreadyExistsException(email)
    }

    private fun checkEmailVerified(user: User) {
        if (user.isEmailVerified) return

        throw EmailNotVerifiedException(user.email)
    }

    private fun tryAuthenticate(request: AuthenticateRequest): User {
        try {
            val user = userService.findByEmail(request.email)
            val isPasswordCorrect = passwordEncoder.matches(request.password, user.password)

            if (!isPasswordCorrect) throw AuthenticationException()

            return user
        } catch (_: Exception) {
            throw AuthenticationException()
        }
    }

    private fun buildTokenResponse(subject: String): TokenResponse = TokenResponse(
        accessToken = jwtProvider.generateToken(subject, TokenType.ACCESS_TOKEN),
        refreshToken = jwtProvider.generateToken(subject, TokenType.REFRESH_TOKEN),
    )
}