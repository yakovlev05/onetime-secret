package ru.yakovlev05.ots.backend.exception

import org.springframework.http.HttpStatus

class EmailNotVerifiedException(vararg any: Any) : BusinessException(
    "Email '%s' не подтвержден. Перейдите по ссылке из письма",
    HttpStatus.UNAUTHORIZED,
    *any
)