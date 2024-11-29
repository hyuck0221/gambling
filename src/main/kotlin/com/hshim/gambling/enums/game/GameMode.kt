package com.hshim.gambling.enums.game

import com.hshim.gambling.database.account.User

enum class GameMode(
    val gameName: String,
    val minCost: Long,
    val maxCost: Long?,
    val canUsePoint: Boolean,
    val onlyPoint: Boolean,
    val endPoint: String,
    icon: String?,
) {
    NORMAL_BETTING(
        gameName = "기본 배팅",
        minCost = 100,
        maxCost = null,
        canUsePoint = false,
        onlyPoint = false,
        endPoint = "normal-betting",
        icon = "normal-betting.png",
    ),
    DICE_BETTING(
        gameName = "주사위 배팅",
        minCost = 100,
        maxCost = null,
        canUsePoint = false,
        onlyPoint = false,
        endPoint = "dice-betting",
        icon = "dice-betting.png",
    ),
    ;
    val icon: String? = icon?.let { "/icon/game/$it" }

    fun getCanUseMoney(user: User): Long {
        return when {
            onlyPoint -> user.point
            canUsePoint -> user.money + user.borrowedMoney + user.point
            else -> user.money
        }
    }

    fun canPlay(user: User) = getCanUseMoney(user) >= minCost
    fun canPlay(paymentCost: Long): Boolean {
        return when {
            minCost > paymentCost -> false
            maxCost != null && maxCost < paymentCost -> false
            else -> true
        }
    }
}