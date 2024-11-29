package com.hshim.gambling.setting.websocket

import com.hshim.gambling.enums.session.WebSocketSessionType
import com.hshim.gambling.model.websocket.BaseEventModel
import io.autocrypt.sakarinblue.universe.util.CommonUtil.convertObject2JsonString
import org.springframework.stereotype.Component
import org.springframework.web.socket.TextMessage
import org.springframework.web.socket.WebSocketSession
import java.util.concurrent.ConcurrentHashMap

@Component
class SessionManager {
    private val sessionGroups: MutableMap<Pair<String, WebSocketSessionType>, WebSocketSession> = ConcurrentHashMap()

    fun addSession(session: WebSocketSession, userId: String, type: WebSocketSessionType) {
        sessionGroups[userId to type]?.close()
        sessionGroups[userId to type] = session
    }

    fun removeSession(session: WebSocketSession, userId:String, type: WebSocketSessionType) {
        sessionGroups.remove(userId to type)
    }

    fun send(userId: String, event: BaseEventModel) {
        sessionGroups[userId to event.sessionType]?.let { session ->
            if (session.isOpen) session.sendMessage(TextMessage(convertObject2JsonString(event)))
        }
    }
}