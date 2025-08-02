package ru.yakovlev05.ots.backend.entity

import jakarta.persistence.*

@Entity
@Table(name = "users")
class User(
    @Column(nullable = false, unique = true) var username: String,
    @Column(nullable = false, unique = true) var email: String,
    @Column(nullable = false) var password: String,
    @Column(nullable = false) var isEmailVerified: Boolean = false,
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) var id: Long? = null
)
