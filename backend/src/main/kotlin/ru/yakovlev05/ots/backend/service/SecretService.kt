package ru.yakovlev05.ots.backend.service

import ru.yakovlev05.ots.backend.dto.secret.SecretCreateRequest
import ru.yakovlev05.ots.backend.dto.secret.SecretCreateResponse
import ru.yakovlev05.ots.backend.entity.User

interface SecretService {
    fun create(request: SecretCreateRequest, principal: User?): SecretCreateResponse
}