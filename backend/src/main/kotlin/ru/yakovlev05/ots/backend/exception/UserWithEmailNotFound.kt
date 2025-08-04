package ru.yakovlev05.ots.backend.exception

import org.springframework.http.HttpStatus

class UserWithEmailNotFound(vararg any: Any) : BusinessException(
    "Пользователь с email '%s' не найден",
    HttpStatus.NOT_FOUND,
    *any
)