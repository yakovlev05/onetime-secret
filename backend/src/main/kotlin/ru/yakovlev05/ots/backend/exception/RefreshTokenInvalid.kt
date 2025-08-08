package ru.yakovlev05.ots.backend.exception

import org.springframework.http.HttpStatus
import ru.yakovlev05.ots.backend.exception.base.BusinessException

class RefreshTokenInvalid : BusinessException(
    "Refresh token не валиден или пользователь удален",
    HttpStatus.UNAUTHORIZED
) {
}