package com.tistory.eclipse4j.core.domain.utils

class MaskingUtils {
    companion object {
        fun maskingEmail(email: String?): String {
            if (email == null) return ""
            val regex = """^([^@]{3})([^@]+)([^@]{0}@)([^@]{3})""".toRegex()
            return email.replace(regex) {
                it.groupValues[1] + "*".repeat(it.groupValues[2].length) + it.groupValues[3] + "*".repeat(it.groupValues[4].length)
            }
        }

        fun maskingPhoneNumber(phoneNumber: String?): String {
            if (phoneNumber == null) return ""
            val regex = """([^@]{5})([^@]+)""".toRegex()
            return phoneNumber.replace(regex) {
                it.groupValues[1] + "*".repeat(it.groupValues[2].length)
            }
        }

        fun maskingLastChar(input: String?): String? {
            if (input == null) return null
            return input.substring(0, input.length - 1) + "*"
        }

        fun maskingAll(input: String?): String {
            if (input == null) return ""
            return "*".repeat(input.length)
        }
    }
}
