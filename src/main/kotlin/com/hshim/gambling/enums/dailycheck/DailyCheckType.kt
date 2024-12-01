package com.hshim.gambling.enums.dailycheck

import java.time.DayOfWeek
import java.time.LocalDateTime

enum class DailyCheckType(
    val type: String,
    val cost: Long,
) {
    WEEK_DAY(
        type = "평일",
        cost = 5000,
    ),
    WEEK_END(
        type = "주말",
        cost = 10000,
    ),
    SEQUENCE(
        type = "연속 출석",
        cost = 1000,
    ),
    ;

    companion object {
        fun getDailyType(date: LocalDateTime = LocalDateTime.now()): DailyCheckType {
            return when (date.dayOfWeek) {
                DayOfWeek.SATURDAY,
                DayOfWeek.SUNDAY -> WEEK_END

                else -> WEEK_DAY
            }
        }
    }
}