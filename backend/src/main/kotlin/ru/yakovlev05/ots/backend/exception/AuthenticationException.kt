package ru.yakovlev05.ots.backend.exception

import org.springframework.http.HttpStatus

class AuthenticationException : BusinessException("Неверный логин/пароль", HttpStatus.UNAUTHORIZED) {
}