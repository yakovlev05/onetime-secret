package ru.yakovlev05.ots.backend.service.impl

import org.springframework.stereotype.Service
import ru.yakovlev05.ots.backend.entity.User
import ru.yakovlev05.ots.backend.exception.UserWithEmailNotFound
import ru.yakovlev05.ots.backend.repository.UserRepository
import ru.yakovlev05.ots.backend.service.JwtUserLoader
import ru.yakovlev05.ots.backend.service.UserService

@Service
class UserServiceImpl(
    private val userRepository: UserRepository
) : UserService, JwtUserLoader {

    override fun loadUserById(id: Long): User? = userRepository.findById(id)

    override fun existsByEmail(email: String): Boolean = userRepository.existsByEmail(email)

    override fun save(user: User) = userRepository.save(user)

    override fun findByEmail(email: String): User =
        userRepository.findByEmail(email) ?: throw UserWithEmailNotFound(email)

    override fun findById(id: Long): User? = userRepository.findById(id)

}