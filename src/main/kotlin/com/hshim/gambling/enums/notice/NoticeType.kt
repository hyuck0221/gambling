package com.hshim.gambling.enums.notice

import com.hshim.gambling.database.account.User

enum class NoticeType (
    val title: String,
    val isOption: Boolean,
    icon: String?,
) {
    PRESENT_POINT(
        title = "포인트 선물",
        isOption = false,
        icon = "present-point.webp",
    ),
    PRESENT_MONEY(
        title = "지원금",
        isOption = false,
        icon = "present-money.webp",
    ),
    DONATE(
        title = "기부금",
        isOption = true,
        icon = "donate.webp",
    ),
    INVITE_GAME(
        title = "게임 초대",
        isOption = true,
        icon = "invite-game.webp",
    ),
    ;

    val icon: String? = icon?.let { "/icon/notice/$it" }
    fun getDescription(
        cost: Long?,
        from: User?,
    ): String {
        return when (this) {
            PRESENT_POINT -> "$cost 포인트 선물이 도착했습니다."
            PRESENT_MONEY -> "$cost 지원금이 도착했습니다."
            DONATE -> "${from?.displayName ?: "알 수 없는 사용자"}님 으로부터 $cost 기부금이 도착했습니다."
            INVITE_GAME -> "${from?.displayName ?: "알 수 없는 사용자"}님이 게임에 초대하였습니다."
        }
    }
}