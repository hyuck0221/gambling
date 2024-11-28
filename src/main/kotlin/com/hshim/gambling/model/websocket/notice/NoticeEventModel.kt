package com.hshim.gambling.model.websocket.notice

import com.hshim.gambling.database.notice.Notice
import com.hshim.gambling.enums.session.WebSocketSessionType.NOTICE
import com.hshim.gambling.model.notice.NoticeResponse
import com.hshim.gambling.model.websocket.BaseEventModel

class NoticeEventModel {
    class NoticeInfo(
        val content: NoticeResponse
    ): BaseEventModel("notice_info", NOTICE) {
        constructor(notice: Notice): this (content = NoticeResponse(notice))
    }
}