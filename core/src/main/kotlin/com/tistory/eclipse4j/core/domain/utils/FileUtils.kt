package com.tistory.eclipse4j.core.domain.utils

class FileUtils {
    companion object {
        private val allowedFileExceptions = listOf("gif", "webp", "jpg", "jpeg", "jpe", "png", "raw", "tif", "tiff")

        fun isImage(ext: String): Boolean {
            return allowedFileExceptions.contains(ext)
        }
    }
}
