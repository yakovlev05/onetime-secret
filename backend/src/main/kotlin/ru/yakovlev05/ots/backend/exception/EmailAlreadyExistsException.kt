package ru.yakovlev05.ots.backend.exception

import org.springframework.http.HttpStatus


class EmailAlreadyExistsException(vararg any: Any) : BusinessException(
    "Email '%s' уже существует",
    HttpStatus.CONFLICT,
    *any
)