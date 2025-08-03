package ru.yakovlev05.ots.backend.exception

import org.springframework.http.HttpStatus

abstract class BusinessException(pattern: String, val status: HttpStatus, vararg any: Any) :
    RuntimeException(pattern.format(*any))