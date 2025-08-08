package ru.yakovlev05.ots.backend.exception

import org.springframework.http.HttpStatus
import ru.yakovlev05.ots.backend.exception.base.BusinessException

class AuthenticationException : BusinessException("Неверный логин/пароль", HttpStatus.UNAUTHORIZED) {
}