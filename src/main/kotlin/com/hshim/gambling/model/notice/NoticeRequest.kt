package com.hshim.gambling.model.notice

import com.hshim.gambling.database.account.User
import com.hshim.gambling.database.notice.Notice
import com.hshim.gambling.database.present.Present
import com.hshim.gambling.enums.notice.NoticeType

class NoticeRequest (
    val noticeType: NoticeType,
    val to: User,
    val from: User?,
    val present: Present?,
    val option: Boolean = true,
) {
    fun toEntity() = Notice(
        user = to,
        present = present,
        noticeType = noticeType,
        description = noticeType.getDescription(
            cost = present?.cost,
            from = from,
            option = option,
        ),
    )
}