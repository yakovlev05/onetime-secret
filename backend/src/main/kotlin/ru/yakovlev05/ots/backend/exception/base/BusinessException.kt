package ru.yakovlev05.ots.backend.exception.base

import org.springframework.http.HttpStatus

abstract class BusinessException(message: String, val status: HttpStatus) :
    RuntimeException(message)