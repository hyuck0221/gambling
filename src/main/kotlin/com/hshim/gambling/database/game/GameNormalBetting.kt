package com.hshim.gambling.database.game

import com.hshim.gambling.database.account.User
import com.hshim.gambling.enums.game.GameMode
import jakarta.persistence.Column
import jakarta.persistence.DiscriminatorValue
import jakarta.persistence.Entity
import jakarta.persistence.Table

@Entity
@Table(name = "game_normal_betting")
@DiscriminatorValue(value = "NORMAL_BETTING")
class GameNormalBetting (
    user: User,
    isWin: Boolean,
    cost: Long,

    @Column(nullable = false)
    val percent: Int,
) : Game(
    user = user,
    isWin = isWin,
    gameMode = GameMode.NORMAL_BETTING,
    cost = cost,
)