package com.hshim.gambling.database.dailycheck.repository

import com.hshim.gambling.database.account.User
import com.hshim.gambling.database.dailycheck.DailyCheck
import org.springframework.data.jpa.repository.JpaRepository
import java.time.LocalDateTime

interface DailyCheckRepository : JpaRepository<DailyCheck, String> {
    fun existsByUserAndCreateDateAfter(user: User, createDate: LocalDateTime): Boolean
    fun existsByUserAndCreateDateBetween(
        user: User,
        startCreateDate: LocalDateTime,
        endCreateDate: LocalDateTime,
    ): Boolean
}