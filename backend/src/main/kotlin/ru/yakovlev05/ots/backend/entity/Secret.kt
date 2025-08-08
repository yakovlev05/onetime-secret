package ru.yakovlev05.ots.backend.entity

import jakarta.persistence.*
import ru.yakovlev05.ots.backend.entity.base.BaseEntity
import java.time.Instant
import java.util.*

@Entity
@Table(name = "secrets")
class Secret(

    @Column(nullable = false) var expiresIn: Instant,
    @Column(nullable = false, columnDefinition = "TEXT") var value: String,
    @ManyToOne @JoinColumn(name = "user_id") var user: User?,
    var password: String? = null,
    var recipientEmail: String? = null,
    var creatorEmail: String? = null,
    @Column(nullable = false) var passAttemptsLeft: Int = 3,
    @Column(nullable = false) var isViewed: Boolean = false,
    @Column(nullable = false) var viewsLeft: Int = 1,
    @Column(unique = true) var shortHash: String? = null,
    @Id var id: String = UUID.randomUUID().toString()
) : BaseEntity()
