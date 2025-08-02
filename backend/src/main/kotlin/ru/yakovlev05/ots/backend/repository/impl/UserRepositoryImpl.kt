package ru.yakovlev05.ots.backend.repository.impl

import jakarta.persistence.EntityManager
import org.springframework.stereotype.Repository
import ru.yakovlev05.ots.backend.entity.User
import ru.yakovlev05.ots.backend.repository.UserRepository
import ru.yakovlev05.ots.backend.repository.base.CrudRepositoryImpl

@Repository
class UserRepositoryImpl(
    private val entityManager: EntityManager
) : UserRepository, CrudRepositoryImpl<User, Long>(entityManager, User::class.java)