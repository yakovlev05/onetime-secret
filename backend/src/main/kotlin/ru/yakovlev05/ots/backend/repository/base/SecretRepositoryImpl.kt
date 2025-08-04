package ru.yakovlev05.ots.backend.repository.base

import jakarta.persistence.EntityManager
import org.springframework.stereotype.Repository
import ru.yakovlev05.ots.backend.entity.Secret
import ru.yakovlev05.ots.backend.repository.SecretRepository

@Repository
class SecretRepositoryImpl(private val entityManager: EntityManager) : SecretRepository,
    CrudRepositoryImpl<Secret, String>(entityManager, Secret::class.java) {

}