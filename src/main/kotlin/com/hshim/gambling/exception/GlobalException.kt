package com.hshim.gambling.exception

import io.autocrypt.sakarinblue.universe.util.ClassUtil.classToMap
import org.springframework.http.HttpStatus
import org.springframework.web.server.ResponseStatusException

enum class GlobalException(
    val message: String,
    val status: HttpStatus = HttpStatus.BAD_REQUEST,
) {
    NOT_FOUND_USER("유저를 찾을 수 없습니다."),
    IS_DISABLED_USER("비활성화된 유저입니다."),
    NOT_FOUND_NOTICE("알림을 찾을 수 없습니다."),
    NOT_FOUND_PRESENT("선물을 찾을 수 없습니다."),
    IS_ALREADY_END_PRESENT("처리 완료된 선물입니다."),
    LARK_MONEY("돈이 부족합니다."),
    ;

    val exception = exception(null)
    fun exception(responseBody: Any?): ResponseStatusException {
        val message = responseBody?.classToMap()?.toString() ?: this.message
        return ResponseStatusException(status, message)
    }
}