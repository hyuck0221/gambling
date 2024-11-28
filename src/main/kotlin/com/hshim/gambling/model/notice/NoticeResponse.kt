package com.hshim.gambling.model.notice

import com.hshim.gambling.database.notice.Notice
import com.hshim.gambling.enums.notice.NoticeType
import io.autocrypt.sakarinblue.universe.util.DateUtil.dateToString

class NoticeResponse (
    val noticeId: String,
    val description: String,
    val noticeType: NoticeType,
    val title: String = noticeType.title,
    val icon: String? = noticeType.icon,
    val isOption: Boolean = noticeType.isOption,
    val date: String,
) {
    constructor(notice: Notice): this (
        noticeId = notice.id,
        description = notice.description,
        noticeType = notice.noticeType,
        date = notice.createDate.dateToString(),
    )
}