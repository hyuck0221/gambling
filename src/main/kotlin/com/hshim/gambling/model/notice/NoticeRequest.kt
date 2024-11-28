package com.hshim.gambling.model.notice

import com.hshim.gambling.database.account.User
import com.hshim.gambling.database.notice.Notice
import com.hshim.gambling.enums.notice.NoticeType

class NoticeRequest (
    val noticeType: NoticeType,
    val cost: Long?,
    val to: User,
    val from: User?,
) {
    fun toEntity() = Notice(
        user = to,
        noticeType = noticeType,
        description = noticeType.getDescription(
            cost = cost,
            from = from,
        ),
    )
}