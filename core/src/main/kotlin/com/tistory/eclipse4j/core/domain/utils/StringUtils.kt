package com.tistory.eclipse4j.core.domain.utils

import java.util.Base64
import java.util.UUID

class StringUtils {
    companion object {
        /**
         * text uuid를 12자리(Default) 문자로 반환한다.
         */
        fun generate12CharUUID(txtUuid: String, len: Int = 12): String {
            val uuidBytes = ByteArray(16)
            val uuid = UUID.fromString(txtUuid)
            val mostSigBits = uuid.mostSignificantBits
            val leastSigBits = uuid.leastSignificantBits
            for (i in 0..7) {
                uuidBytes[i] = (mostSigBits shr (8 * (7 - i)) and 0xFF).toByte()
            }
            for (i in 8..15) {
                uuidBytes[i] = (leastSigBits shr (8 * (15 - i)) and 0xFF).toByte()
            }
            val base64Encoded = Base64.getUrlEncoder().withoutPadding().encodeToString(uuidBytes)
            return base64Encoded.take(len)
        }
    }
}
