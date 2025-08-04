package ru.yakovlev05.ots.backend.filter

import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.http.HttpHeaders
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter
import ru.yakovlev05.ots.backend.provider.JwtProvider
import ru.yakovlev05.ots.backend.provider.TokenType
import ru.yakovlev05.ots.backend.service.JwtUserLoader

private const val BEARER_PREFIX = "Bearer "

@Component
class JwtFilter(private val jwtProvider: JwtProvider, private val jwtUserLoader: JwtUserLoader) :
    OncePerRequestFilter() {

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        val token = resolveToken(request)
        if (token != null && jwtProvider.isValid(token, TokenType.ACCESS_TOKEN)) {
            setUpSecurityContextHolder(token)
        }

        filterChain.doFilter(request, response)
    }

    private fun resolveToken(request: HttpServletRequest): String? {
        val header = request.getHeader(HttpHeaders.AUTHORIZATION)
        if (header != null && header.contains(BEARER_PREFIX)) {
            return header.substring(BEARER_PREFIX.length)
        }

        return null
    }

    private fun setUpSecurityContextHolder(token: String) {
        val userId = jwtProvider.extractSubject(token).toLong()

        // Можно в бд не ходить каждый раз
        jwtUserLoader.loadUserById(userId)?.let {
            SecurityContextHolder.getContext().authentication = UsernamePasswordAuthenticationToken(it, null, arrayListOf())
        }
        // Понадобится authorities - сделаем wrapper
    }
}