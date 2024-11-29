package com.hshim.gambling.api.game.normalbetting

import com.hshim.gambling.model.game.normalbetting.NormalBettingIssueRequest
import com.hshim.gambling.model.game.normalbetting.NormalBettingIssueResponse
import com.hshim.gambling.model.game.normalbetting.NormalBettingResultResponse
import com.hshim.gambling.service.game.normalbetting.NormalBettingService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/game/normal-betting")
class NormalBettingController(private val normalBettingService: NormalBettingService) {
    @PostMapping("/issue")
    fun issue(@RequestBody req: NormalBettingIssueRequest): NormalBettingIssueResponse {
        return normalBettingService.issue(req)
    }

    @GetMapping("{id}/result")
    fun result(@PathVariable id: String): NormalBettingResultResponse {
        return normalBettingService.result(id)
    }
}