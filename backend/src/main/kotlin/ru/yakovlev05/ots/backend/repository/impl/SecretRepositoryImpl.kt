package ru.yakovlev05.ots.backend.repository.impl

import jakarta.persistence.EntityManager
import jakarta.persistence.PersistenceContext
import org.springframework.stereotype.Repository
import ru.yakovlev05.ots.backend.entity.Secret
import ru.yakovlev05.ots.backend.repository.SecretRepository
import ru.yakovlev05.ots.backend.repository.base.CrudRepositoryImpl

@Repository
class SecretRepositoryImpl(@PersistenceContext private val entityManager: EntityManager) : SecretRepository,
    CrudRepositoryImpl<Secret, String>(entityManager, Secret::class.java) {

    override fun existsShortHash(shortHash: String): Boolean {
        return entityManager.createQuery("SELECT 1 FROM Secret s where s.shortHash = :shortHash")
            .setParameter("shortHash", shortHash)
            .resultList
            .isNotEmpty()
    }

}