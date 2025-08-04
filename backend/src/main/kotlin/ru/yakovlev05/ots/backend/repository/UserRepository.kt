package ru.yakovlev05.ots.backend.repository

import ru.yakovlev05.ots.backend.entity.User
import ru.yakovlev05.ots.backend.repository.base.CrudRepository

interface UserRepository : CrudRepository<User, Long> {
    fun findByEmail(email: String): User?
    fun existsByEmail(email: String): Boolean
}