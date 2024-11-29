package com.hshim.gambling.database.game.repository

import com.hshim.gambling.database.game.Game
import org.springframework.data.jpa.repository.JpaRepository

interface GameRepository : JpaRepository<Game, String> {
}