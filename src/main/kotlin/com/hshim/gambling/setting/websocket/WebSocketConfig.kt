package com.hshim.gambling.setting.websocket

import com.hshim.gambling.setting.websocket.notice.NoticeWebSocketHandler
import org.springframework.context.annotation.Configuration
import org.springframework.web.socket.config.annotation.EnableWebSocket
import org.springframework.web.socket.config.annotation.WebSocketConfigurer
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry

@Configuration
@EnableWebSocket
class WebSocketConfig(
    private val noticeWebSocketHandler: NoticeWebSocketHandler,
) : WebSocketConfigurer {
    override fun registerWebSocketHandlers(registry: WebSocketHandlerRegistry) {
        registry.addHandler(noticeWebSocketHandler, "/notice")
            .setAllowedOrigins("*")
    }
}