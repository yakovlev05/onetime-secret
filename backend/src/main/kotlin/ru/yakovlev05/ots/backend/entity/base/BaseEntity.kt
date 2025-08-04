package ru.yakovlev05.ots.backend.entity.base

import jakarta.persistence.MappedSuperclass
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.time.Instant

@MappedSuperclass
abstract class BaseEntity(
    @UpdateTimestamp val updatedAt: Instant? = null,
    @CreationTimestamp val createdAt: Instant? = null
)