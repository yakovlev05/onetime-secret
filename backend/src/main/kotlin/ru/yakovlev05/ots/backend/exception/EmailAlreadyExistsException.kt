package ru.yakovlev05.ots.backend.exception

import org.springframework.http.HttpStatus
import ru.yakovlev05.ots.backend.exception.base.BusinessException


class EmailAlreadyExistsException(email: String) : BusinessException(
    "Email $email уже существует",
    HttpStatus.CONFLICT,
)