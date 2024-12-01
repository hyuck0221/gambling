package com.hshim.gambling.enums.present

import com.hshim.gambling.enums.notice.NoticeType

enum class PresentType(
    val noticeType: NoticeType,
) {
    PRESENT_POINT(
        noticeType = NoticeType.PRESENT_POINT,
    ),
    PRESENT_MONEY(
        noticeType = NoticeType.PRESENT_MONEY,
    ),
    DONATE(
        noticeType = NoticeType.DONATE,
    ),
    DAILY_CHECK(
        noticeType = NoticeType.DAILY_CHECK,
    )
    ;

    companion object {
        fun findByNoticeType(noticeType: NoticeType) = entries.find { it.noticeType == noticeType }
    }
}