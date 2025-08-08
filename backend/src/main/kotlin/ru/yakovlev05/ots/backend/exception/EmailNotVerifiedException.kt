package ru.yakovlev05.ots.backend.exception

import org.springframework.http.HttpStatus
import ru.yakovlev05.ots.backend.exception.base.BusinessException

class EmailNotVerifiedException(email: String) : BusinessException(
    "Email $email не подтвержден. Перейдите по ссылке из письма",
    HttpStatus.UNAUTHORIZED
)