package com.hshim.gambling.enums.present

enum class PresentStatus(
    private val description: String,
) {
    WAIT("대기중"),
    APPROVE("승인"),
    REJECT("반려"),
}