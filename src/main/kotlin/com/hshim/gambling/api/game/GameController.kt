package com.hshim.gambling.api.game

import com.hshim.gambling.enums.game.GameMode
import com.hshim.gambling.model.game.GameResponse
import com.hshim.gambling.service.game.GameService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/game")
class GameController(private val gameService: GameService) {
    @GetMapping("/list")
    fun getGameInfos(
        @RequestParam(required = false) search: String?,
    ): List<GameResponse> {
        return gameService.getGameInfos(search)
    }

    @GetMapping
    fun info(
        @RequestParam(required = false) gameMode: GameMode,
    ): GameResponse {
        return gameService.info(gameMode)
    }
}