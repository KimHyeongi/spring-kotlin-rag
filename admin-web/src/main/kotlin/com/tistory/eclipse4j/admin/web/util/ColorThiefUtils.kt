package com.tistory.eclipse4j.admin.web.util

import io.github.oshai.kotlinlogging.KotlinLogging
import com.tistory.eclipse4j.core.domain.utils.FileUtils
import org.apache.commons.imaging.Imaging
import java.awt.Color
import java.awt.image.BufferedImage
import java.io.InputStream

class ColorThiefUtils {
    companion object {
        private val log = KotlinLogging.logger { }
        private const val DEFAULT_COLOR = "#000000"

        fun getDominantColorHex(fileExtension: String, stream: InputStream): String {
            if (!FileUtils.isImage(fileExtension)) {
                return DEFAULT_COLOR
            }
            try {
                val image: BufferedImage = Imaging.getBufferedImage(stream)
                val colorHistogram = mutableMapOf<Color, Int>()
                for (y in 0 until image.height) {
                    for (x in 0 until image.width) {
                        val rgb = image.getRGB(x, y)
                        val color = Color(rgb)
                        colorHistogram[color] = colorHistogram.getOrDefault(color, 0) + 1
                    }
                }
                val dominantColor = colorHistogram.maxByOrNull { it.value }?.key
                    ?: throw IllegalArgumentException("이미지에서 색상을 추출할 수 없습니다.")
                val color = Color(dominantColor.rgb, true)
                return String.format("#%02X%02X%02X", color.red, color.green, color.blue)
            } catch (e: Exception) {
                log.error(e) { "색을 추출하지 못했습니다. 검정으로 대체합니다." }
            }
            return DEFAULT_COLOR
        }
    }
}
