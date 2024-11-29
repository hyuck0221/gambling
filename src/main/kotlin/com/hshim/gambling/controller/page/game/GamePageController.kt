package com.hshim.gambling.controller.page.game

import com.hshim.gambling.enums.game.GameMode
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam

@Controller
@RequestMapping("/page/game")
class GamePageController {
    @GetMapping
    fun play(@RequestParam(required = true) gameMode: GameMode) = "page/game/${gameMode.endPoint}"
}