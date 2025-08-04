package ru.yakovlev05.ots.backend.exception

import org.springframework.http.HttpStatus

class RefreshTokenInvalid : BusinessException(
    "Refresh token не валиден или пользователь удален",
    HttpStatus.UNAUTHORIZED
) {
}