package com.hshim.gambling.model.game.normalbetting

import com.hshim.gambling.database.game.GameNormalBetting

class NormalBettingResultResponse(
    val isWin: Boolean,
) {
    constructor(game: GameNormalBetting) : this(
        isWin = game.isWin,
    )
}