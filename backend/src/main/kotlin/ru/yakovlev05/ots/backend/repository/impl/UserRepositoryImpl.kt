package ru.yakovlev05.ots.backend.repository.impl

import jakarta.persistence.EntityManager
import jakarta.persistence.PersistenceContext
import org.springframework.stereotype.Repository
import ru.yakovlev05.ots.backend.entity.User
import ru.yakovlev05.ots.backend.repository.UserRepository
import ru.yakovlev05.ots.backend.repository.base.CrudRepositoryImpl

@Repository
class UserRepositoryImpl(
    @PersistenceContext private val entityManager: EntityManager
) : UserRepository, CrudRepositoryImpl<User, Long>(entityManager, User::class.java) {

    override fun findByEmail(email: String): User? {
        return entityManager.createQuery("SELECT u FROM User u WHERE u.email = :email", User::class.java)
            .setParameter("email", email)
            .resultList
            .firstOrNull()
    }

    override fun existsByEmail(email: String): Boolean {
        return entityManager.createQuery("SELECT 1 FROM User u WHERE u.email = :email")
            .setParameter("email", email)
            .resultList
            .isNotEmpty()
    }

}