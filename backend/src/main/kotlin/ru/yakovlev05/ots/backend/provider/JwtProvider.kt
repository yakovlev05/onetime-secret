package ru.yakovlev05.ots.backend.provider

import com.nimbusds.jose.JWSAlgorithm
import com.nimbusds.jose.JWSHeader
import com.nimbusds.jose.crypto.MACSigner
import com.nimbusds.jose.crypto.MACVerifier
import com.nimbusds.jwt.JWTClaimsSet
import com.nimbusds.jwt.JWTParser
import com.nimbusds.jwt.SignedJWT
import org.springframework.stereotype.Component
import ru.yakovlev05.ots.backend.props.JwtProps
import java.util.*

private const val TOKEN_TYPE = "token_type"

@Component
class JwtProvider(
    private val jwtProps: JwtProps
) {
    private val signer = MACSigner(jwtProps.secretKey)
    private val verifier = MACVerifier(jwtProps.secretKey)

    fun generateToken(subject: String, tokenType: TokenType): String {
        return when (tokenType) {
            TokenType.ACCESS_TOKEN -> sign(getClaimsSet(subject, tokenType, jwtProps.accessTokenValidity))
            TokenType.REFRESH_TOKEN -> sign(getClaimsSet(subject, tokenType, jwtProps.refreshTokenValidity))
        }
    }

    fun isValid(token: String, tokenType: TokenType): Boolean {
        try {
            val signedJWT = SignedJWT.parse(token)
            return signedJWT.verify(verifier)
                    && signedJWT.jwtClaimsSet.expirationTime > Date()
                    && signedJWT.jwtClaimsSet.getClaim(TOKEN_TYPE) == tokenType.name
        } catch (_: Exception) {
            return false
        }
    }

    fun extractSubject(token: String): String = JWTParser.parse(token).jwtClaimsSet.subject

    private fun getClaimsSet(subject: String, tokenType: TokenType, validity: Long): JWTClaimsSet {
        return JWTClaimsSet.Builder()
            .subject(subject)
            .claim(TOKEN_TYPE, tokenType)
            .expirationTime(Date(System.currentTimeMillis() + validity * 1000))
            .build()
    }

    private fun sign(claimsSet: JWTClaimsSet): String {
        val signedJWT = SignedJWT(JWSHeader(JWSAlgorithm.HS256), claimsSet)
        signedJWT.sign(signer)
        return signedJWT.serialize()
    }
}