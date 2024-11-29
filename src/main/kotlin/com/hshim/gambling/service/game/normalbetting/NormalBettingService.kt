package com.hshim.gambling.service.game.normalbetting

import com.hshim.gambling.database.game.repository.GameNormalBettingRepository
import com.hshim.gambling.enums.game.GameMode
import com.hshim.gambling.exception.GlobalException.NOT_FOUND_GAME
import com.hshim.gambling.model.game.normalbetting.NormalBettingIssueRequest
import com.hshim.gambling.model.game.normalbetting.NormalBettingIssueResponse
import com.hshim.gambling.model.game.normalbetting.NormalBettingResultResponse
import com.hshim.gambling.service.account.user.UserService
import com.hshim.gambling.service.game.GameService
import io.autocrypt.sakarinblue.universe.util.CommonUtil.uuid
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import kotlin.random.Random

@Service
class NormalBettingService(
    private val userService: UserService,
    private val gameService: GameService,
    private val gameNormalBettingRepository: GameNormalBettingRepository,
) {
    private val gameMode = GameMode.NORMAL_BETTING
    private val minPercent = 20
    private val maxPercent = 80

    @Transactional
    fun issue(req: NormalBettingIssueRequest): NormalBettingIssueResponse {
        gameService.canPlay(gameMode)
        val user = userService.getUser()
        val percent = Random(uuid().hashCode()).nextInt(minPercent, maxPercent + 1)
        val target = Random(uuid().hashCode()).nextInt(100) + 1
        val isWin = target <= percent

        when (isWin) {
            true -> user.money += req.cost
            false -> user.lose(gameMode, req.cost)
        }

        val game = gameNormalBettingRepository.save(req.toEntity(user, percent, isWin ))
        return NormalBettingIssueResponse(game)
    }

    @Transactional(readOnly = true)
    fun result(id: String): NormalBettingResultResponse {
        val game = gameNormalBettingRepository.findByIdOrNull(id)
            ?: throw NOT_FOUND_GAME.exception
        return NormalBettingResultResponse(game)
    }
}