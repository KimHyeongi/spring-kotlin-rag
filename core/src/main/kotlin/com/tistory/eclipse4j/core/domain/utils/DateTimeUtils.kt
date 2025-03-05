package com.tistory.eclipse4j.core.domain.utils

import java.time.*
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit

class DateTimeUtils {

    companion object {

        fun patternYYYYMMDD(): DateTimeFormatter {
            return DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
        }

        fun startedTime(param: LocalDateTime): LocalDateTime {
            return param.with(LocalTime.MIN)
        }

        fun endedTime(param: LocalDateTime): LocalDateTime {
            return param.with(LocalTime.MAX)
        }

        fun serviceStartedAt(): LocalDateTime {
            return LocalDateTime.of(2024, Month.JANUARY, 1, 0, 0)
        }

        fun todayToTxt(): String {
            return localDateToTxt(LocalDate.now())
        }

        fun localDateToTxt(date: LocalDate): String {
            return date.format(DateTimeFormatter.ISO_DATE)
        }

        /** 서울 시간을 UTC 기준으로 +9시간 표기 */
        fun localDateTimeToUtcZoneIsSeoul(dateTime: LocalDateTime): String {
            val zoneId = ZoneId.of("Asia/Seoul")
            val zonedDateTime: ZonedDateTime = dateTime.atZone(ZoneOffset.UTC)
            return zonedDateTime.withZoneSameInstant(zoneId).format(DateTimeFormatter.ISO_OFFSET_DATE_TIME)
        }

        /** 서울 시간을 UTC Zero 로 변환(-9시간)해서 반환 */
        fun localDateTimeToUtc(dateTime: LocalDateTime): String {
            val zoneId = ZoneId.of("Asia/Seoul")
            val zonedDateTime: ZonedDateTime = dateTime.atZone(zoneId)
            return zonedDateTime.withZoneSameInstant(ZoneOffset.UTC).format(DateTimeFormatter.ISO_OFFSET_DATE_TIME)
        }

        fun toLocalDate(txt: String): LocalDate {
            return LocalDate.parse(txt, DateTimeFormatter.ISO_DATE)
        }

        fun toTimestamp(localDateTime: LocalDateTime): Long {
            return localDateTime.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli()
        }

        fun timestampToLocalDateTime(timestamp: Long): LocalDateTime {
            return LocalDateTime.ofInstant(
                Instant.ofEpochMilli(timestamp),
                ZoneOffset.systemDefault()
            )
        }

        fun todayFormatString(): String {
            return LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))
        }

        fun isEqualToSecond(dateTime1: LocalDateTime, dateTime2: LocalDateTime): Boolean {
            return dateTime1.truncatedTo(ChronoUnit.SECONDS) == dateTime2.truncatedTo(ChronoUnit.SECONDS)
        }

        fun getSeoulZoneId(): ZoneId {
            return ZoneId.of("Asia/Seoul")
        }

        fun getUtcZoneId(): ZoneId {
            return ZoneId.of("UTC")
        }

        /**
         * UTC 시간 값을 Asia/Seoul 기준으로 변환합니다.
         *
         * @param utcDateTime 변경할 시간
         * @return Asia/Seoul 기준으로 변환된 시간
         */
        fun convertToKoreanTime(utcDateTime: LocalDateTime): LocalDateTime {
            return utcDateTime.atZone(getUtcZoneId()).withZoneSameInstant(getSeoulZoneId()).toLocalDateTime()
        }

        fun convertToUtcTime(koreanDateTime: LocalDateTime): LocalDateTime {
            return koreanDateTime.atZone(getSeoulZoneId()).withZoneSameInstant(getUtcZoneId()).toLocalDateTime()
        }
    }
}
