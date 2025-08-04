package ru.yakovlev05.ots.backend.repository

import ru.yakovlev05.ots.backend.entity.Secret
import ru.yakovlev05.ots.backend.repository.base.CrudRepository

interface SecretRepository : CrudRepository<Secret, String> {
}