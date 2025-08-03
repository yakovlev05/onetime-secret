# Как сгенерировать секретный ключ для JWS алгоритма (HMAC)

Сгенерируем ключ для HS256 - HMAC with SHA-256.
Нужен ключ не меньше 256-bit, то есть минимум 32-byte.

```kotlin
fun generate(): String {
    val random = SecureRandom() // java.security.SecureRandom
    val secret = ByteArray(32) // 256-bit = 32-byte
    random.nextBytes(secret) // Генерируем

    return Base64.getEncoder().encodeToString(secret)
}

fun main() {
    println(generate())
}

// 6R3mNOm1I0eH4bH/pSrVro7OuRQqDxa12r1v3M0mY9s=
```

**Зачем создавать массив байт и кодировать в BASE64?**


Ключ будет использоваться в текстовом формате (а именно будет записан в _application.properties_).
В таком случае количество вариантов байтов для генерации уменьшается (только человекочитаемые), надежность ключа
снижается. Но если генерировать полностью рандомные байты, то их представить в строке не получится, поэтому используем BASE64.

