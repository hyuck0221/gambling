package com.hshim.gambling.model.game.normalbetting

import com.hshim.gambling.database.account.User
import com.hshim.gambling.database.game.GameNormalBetting

class NormalBettingIssueRequest (
    val cost: Long,
) {
    fun toEntity(
        user: User,
        percent: Int,
        isWin: Boolean,
    ) = GameNormalBetting(
        user = user,
        isWin = isWin,
        percent = percent,
        cost = cost,
    )
}