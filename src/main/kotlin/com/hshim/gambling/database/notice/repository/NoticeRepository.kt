package com.hshim.gambling.database.notice.repository

import com.hshim.gambling.database.account.User
import com.hshim.gambling.database.notice.Notice
import org.springframework.data.jpa.repository.JpaRepository

interface NoticeRepository : JpaRepository<Notice, String> {
    fun findAllByUserOrderByCreateDateDesc(user: User): List<Notice>
    fun findAllByUserAndIsReadOrderByCreateDateDesc(user: User, isRead: Boolean): List<Notice>
}