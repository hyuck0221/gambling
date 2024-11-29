package com.hshim.gambling.setting.websocket.notice

import com.hshim.gambling.enums.session.WebSocketSessionType
import com.hshim.gambling.setting.websocket.SessionManager
import org.springframework.web.socket.CloseStatus
import org.springframework.web.socket.WebSocketSession
import org.springframework.web.socket.handler.TextWebSocketHandler

class NoticeWebSocketHandler(private val sessionManager: SessionManager) : TextWebSocketHandler() {
    override fun afterConnectionEstablished(session: WebSocketSession) {
        val userId = session.attributes["userId"] as String
        sessionManager.addSession(session, userId, WebSocketSessionType.NOTICE)
    }

    override fun afterConnectionClosed(session: WebSocketSession, status: CloseStatus) {
        val userId = session.attributes["userId"] as String
        sessionManager.removeSession(session, userId, WebSocketSessionType.NOTICE)
    }
}