package com.hshim.gambling.model.game.normalbetting

import com.hshim.gambling.database.game.GameNormalBetting

class NormalBettingIssueResponse(
    val bettingId: String,
    val percent: Int,
) {
    constructor(gameNormalBetting: GameNormalBetting) : this(
        bettingId = gameNormalBetting.id,
        percent = gameNormalBetting.percent,
    )
}