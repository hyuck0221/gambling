package com.hshim.gambling.setting.websocket

import com.hshim.gambling.setting.websocket.notice.NoticeWebSocketHandler
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class AppConfig(private val sessionManager: SessionManager) {
    @Bean
    fun noticeWebSocketHandler(): NoticeWebSocketHandler {
        return NoticeWebSocketHandler(sessionManager)
    }
}