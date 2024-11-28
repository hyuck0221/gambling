package com.hshim.gambling.model.websocket

import com.hshim.gambling.enums.session.WebSocketSessionType
import net.minidev.json.annotate.JsonIgnore

open class BaseEventModel (
    val eventName: String,

    @JsonIgnore
    val sessionType: WebSocketSessionType,
)