package ru.yakovlev05.ots.backend.repository.base

import jakarta.persistence.EntityManager
import jakarta.transaction.Transactional

abstract class CrudRepositoryImpl<T, ID>(
    private val entityManager: EntityManager,
    private val clazz: Class<T>
) {

    @Transactional
    open fun save(entity: T) = entityManager.persist(entity)

    open fun findById(id: ID): T? = entityManager.find(clazz, id)

    @Transactional
    open fun update(entity: T): T = entityManager.merge(entity)

    @Transactional
    open fun deleteById(id: ID) {
        val entity = findById(id)
        entity?.let { entityManager.remove(it) }
    }
}