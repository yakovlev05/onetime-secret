package ru.yakovlev05.ots.backend.repository.base

interface CrudRepository<T, ID> {
    fun save(entity: T)
    fun findById(id: ID): T?
    fun update(entity: T): T
    fun deleteById(id: ID)
}