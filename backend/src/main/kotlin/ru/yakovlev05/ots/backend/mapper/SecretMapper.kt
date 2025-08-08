package ru.yakovlev05.ots.backend.mapper

import org.springframework.security.crypto.password.PasswordEncoder
import ru.yakovlev05.ots.backend.dto.secret.SecretCreateRequest
import ru.yakovlev05.ots.backend.dto.secret.SecretCreateResponse
import ru.yakovlev05.ots.backend.entity.Secret
import java.time.Instant
import java.time.temporal.ChronoUnit


fun SecretCreateRequest.toSecret(passwordEncoder: PasswordEncoder): Secret {
    return Secret(
        expiresIn = Instant.now().plus(lifetime, ChronoUnit.SECONDS),
        value = value,
        user = null,
        password = if (password != null) passwordEncoder.encode(password) else null,
        recipientEmail = recipientEmail,
        creatorEmail = creatorEmail,
        viewsLeft = maxViews,
    )
}

fun Secret.toSecretCreateResponse(compactUrl: (String?) -> String?): SecretCreateResponse {
    return SecretCreateResponse(
        shortUrl = compactUrl(shortHash),
        url = compactUrl(id)
    )
}