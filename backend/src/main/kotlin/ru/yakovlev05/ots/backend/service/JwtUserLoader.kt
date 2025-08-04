package ru.yakovlev05.ots.backend.service

import ru.yakovlev05.ots.backend.entity.User

interface JwtUserLoader {
    fun loadUserById(id: Long): User?
}