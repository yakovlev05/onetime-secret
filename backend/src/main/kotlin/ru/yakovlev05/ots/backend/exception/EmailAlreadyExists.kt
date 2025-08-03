package ru.yakovlev05.ots.backend.exception

import org.springframework.http.HttpStatus


class EmailAlreadyExists(vararg any: Any) : BusinessException(
    "Email '%s' уже существует",
    HttpStatus.CONFLICT,
    *any
)