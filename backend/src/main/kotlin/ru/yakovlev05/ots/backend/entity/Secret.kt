package ru.yakovlev05.ots.backend.entity

import jakarta.persistence.*
import ru.yakovlev05.ots.backend.entity.base.BaseEntity
import java.time.Instant

@Entity
@Table(name = "secrets")
class Secret(

    @Column(nullable = false) var expiresIn: Instant,
    @Column(nullable = false, columnDefinition = "TEXT") var value: String,
    @ManyToOne @JoinColumn(name = "user_id", nullable = false) var user: User,
    var password: String? = null,
    var recipientEmail: String? = null,
    var creatorEmail: String? = null,
    @Column(nullable = false) var maxViews: Int = 1,
    @Column(nullable = false) var passAttemptsLeft: Int = 0,
    @Column(nullable = false) var isViewed: Boolean = false,
    @Column(nullable = false) var views: Int = 0,
    @Column(unique = true) var shortHash: String? = null,
    @Id @GeneratedValue(strategy = GenerationType.UUID) var id: String? = null
) : BaseEntity()
