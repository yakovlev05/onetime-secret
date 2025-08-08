package ru.yakovlev05.ots.backend.exception

import org.springframework.http.HttpStatus
import ru.yakovlev05.ots.backend.exception.base.BusinessException

class UserWithEmailNotFound(email: String) : BusinessException(
    "Пользователь с email $email не найден",
    HttpStatus.NOT_FOUND,
)