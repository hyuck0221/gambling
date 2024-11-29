package com.hshim.gambling.database.game.repository

import com.hshim.gambling.database.game.GameNormalBetting
import org.springframework.data.jpa.repository.JpaRepository

interface GameNormalBettingRepository : JpaRepository<GameNormalBetting, String> {
}