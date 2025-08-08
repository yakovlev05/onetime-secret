package ru.yakovlev05.ots.backend.service.impl

import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import ru.yakovlev05.ots.backend.dto.secret.SecretCreateRequest
import ru.yakovlev05.ots.backend.dto.secret.SecretCreateResponse
import ru.yakovlev05.ots.backend.entity.Secret
import ru.yakovlev05.ots.backend.entity.User
import ru.yakovlev05.ots.backend.mapper.toSecret
import ru.yakovlev05.ots.backend.mapper.toSecretCreateResponse
import ru.yakovlev05.ots.backend.props.AppProps
import ru.yakovlev05.ots.backend.repository.SecretRepository
import ru.yakovlev05.ots.backend.service.SecretService

private const val LENGTH_SHORT_HASH = 7

@Service
class SecretServiceImpl(
    private val passwordEncoder: PasswordEncoder,
    private val secretRepository: SecretRepository,
    private val appProps: AppProps
) :
    SecretService {

    override fun create(request: SecretCreateRequest, principal: User?): SecretCreateResponse {
        val secret = request.toSecret(passwordEncoder)
        setShortHash(secret)

        principal?.let { secret.user = principal }

        secretRepository.save(secret)

        return secret.toSecretCreateResponse(this::generateUrl)
    }

    private fun generateShortHash(id: String) = id.replace("-", "").substring(0, LENGTH_SHORT_HASH)

    private fun generateUrl(str: String?): String? {
        str ?: return null
        return "${appProps.hostUrl}/s/${str.replace("-", "")}"
    }

    private fun setShortHash(secret: Secret) {
        val hash = generateShortHash(secret.id)
        if (secretRepository.existsShortHash(hash)) return

        secret.shortHash = hash
    }
}