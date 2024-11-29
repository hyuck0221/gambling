package com.hshim.gambling.model.game

import com.hshim.gambling.database.account.User
import com.hshim.gambling.enums.game.GameMode

class GameResponse (
    val name: String,
    val minCost: Long,
    val maxCost: Long?,
    val canUsePoint: Boolean,
    val onlyPoint: Boolean,
    val icon: String?,
    val gameMode: GameMode,
    val canUseCost: Long,
) {
    constructor(
        gameMode: GameMode,
        user: User,
    ) : this(
        name = gameMode.gameName,
        minCost = gameMode.minCost,
        maxCost = gameMode.maxCost,
        canUsePoint = gameMode.canUsePoint,
        onlyPoint = gameMode.onlyPoint,
        icon = gameMode.icon,
        gameMode = gameMode,
        canUseCost = gameMode.getCanUseMoney(user),
    )
}