package com.hshim.gambling.setting.websocket.notice

import com.hshim.gambling.enums.session.WebSocketSessionType
import com.hshim.gambling.setting.websocket.SessionManager
import org.springframework.web.socket.CloseStatus
import org.springframework.web.socket.WebSocketSession
import org.springframework.web.socket.handler.TextWebSocketHandler

class NoticeWebSocketHandler(private val sessionManager: SessionManager) : TextWebSocketHandler() {
    override fun afterConnectionEstablished(session: WebSocketSession) {
        sessionManager.addSession(session, WebSocketSessionType.NOTICE)
    }

    override fun afterConnectionClosed(session: WebSocketSession, status: CloseStatus) {
        sessionManager.removeSession(session, WebSocketSessionType.NOTICE)
    }
}