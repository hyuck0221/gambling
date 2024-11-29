package com.hshim.gambling.service.game

import com.hshim.gambling.enums.game.GameMode
import com.hshim.gambling.exception.GlobalException.LARK_MONEY
import com.hshim.gambling.model.game.GameResponse
import com.hshim.gambling.service.account.user.UserService
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class GameService(
    private val userService: UserService,
) {
    private val maxGameSize = 5

    fun getGameInfos(search: String?): List<GameResponse> {
        var cnt = 0
        val user = userService.getUser()
        val gameModes = GameMode.entries.filter {
            if (cnt > maxGameSize) return@filter false
            val isSuccess = when (search) {
                null -> true
                else -> it.gameName.contains(search)
            }
            if(isSuccess) cnt++
            isSuccess
        }
        return gameModes.map { GameResponse(it, user) }
    }

    fun canPlay(gameMode: GameMode) {
        val user = userService.getUser()
        if (!gameMode.canPlay(user)) throw LARK_MONEY.exception
    }

    @Transactional(readOnly = true)
    fun info(gameMode: GameMode): GameResponse {
        val user = userService.getUser()
        return GameResponse(gameMode, user)
    }
}