package com.tistory.eclipse4j.core.domain.utils

import org.springframework.security.crypto.password.PasswordEncoder
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException

class Sha512PasswordEncoder : PasswordEncoder {
    override fun encode(rawPassword: CharSequence): String {
        return hashWithSHA512(rawPassword.toString())
    }

    override fun matches(
        rawPassword: CharSequence,
        encodedPassword: String
    ): Boolean {
        val hashedPassword = encode(rawPassword)
        return encodedPassword == hashedPassword
    }

    private fun hashWithSHA512(input: String): String {
        val result = StringBuilder()
        try {
            val md = MessageDigest.getInstance("SHA-512")
            md.update("salt".toByteArray())
            val digested = md.digest(input.toByteArray())
            for (b in digested) {
                result.append(Integer.toHexString(0xFF and b.toInt()))
            }
        } catch (ne: NoSuchAlgorithmException) {
            throw RuntimeException("Bad algorithm")
        }
        return result.toString()
    }
}
