package com.hshim.gambling.model.websocket

import com.hshim.gambling.enums.session.WebSocketSessionType

open class BaseEventModel (
    val eventName: String,
    val sessionType: WebSocketSessionType,
)