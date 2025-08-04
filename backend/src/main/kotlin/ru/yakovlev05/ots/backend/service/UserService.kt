package ru.yakovlev05.ots.backend.service

import ru.yakovlev05.ots.backend.entity.User

interface UserService {
    fun existsByEmail(email: String): Boolean
    fun save(user: User)
    fun findByEmail(email: String): User
    fun findById(id: Long): User?
}