package ru.yakovlev05.ots.backend.mapper

import ru.yakovlev05.ots.backend.dto.auth.RegisterRequest
import ru.yakovlev05.ots.backend.entity.User

fun RegisterRequest.toUser(hashedPassword: String): User {
    return User(
        name = this.name,
        email = this.email,
        password = hashedPassword
    )
}