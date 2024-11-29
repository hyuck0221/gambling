package com.hshim.gambling.service.present

import com.hshim.gambling.database.present.Present
import com.hshim.gambling.database.present.repository.PresentRepository
import com.hshim.gambling.enums.notice.NoticeType
import com.hshim.gambling.enums.present.PresentStatus
import com.hshim.gambling.enums.present.PresentType
import com.hshim.gambling.exception.GlobalException
import com.hshim.gambling.exception.GlobalException.*
import com.hshim.gambling.model.notice.NoticeRequest
import com.hshim.gambling.service.account.user.UserService
import com.hshim.gambling.service.notice.NoticeService
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class PresentService(
    private val presentRepository: PresentRepository,
    private val noticeService: NoticeService,
    private val userService: UserService,
) {
    @Transactional(readOnly = true)
    fun getPresent(id: String): Present {
        return presentRepository.findByIdOrNull(id)
            ?: throw NOT_FOUND_PRESENT.exception
    }

    @Transactional
    fun update(
        id: String,
        status: PresentStatus,
    ) {
        val present = getPresent(id)
        if (status == PresentStatus.WAIT) return
        if (present.status != PresentStatus.WAIT) throw IS_ALREADY_END_PRESENT.exception
        when (status) {
            PresentStatus.APPROVE -> present.approve()
            PresentStatus.REJECT -> present.reject()
            else -> null
        }
        present.status = status
        noticeService.check(present)
    }

    private fun Present.approve() {
        when (type) {
            PresentType.PRESENT_POINT -> toUser.point += cost
            PresentType.PRESENT_MONEY -> toUser.money += cost
            PresentType.DONATE -> {
                toUser.money += cost
                val noticeRequest = NoticeRequest(
                    noticeType = NoticeType.DONATE_RESPONSE,
                    to = this.fromUser!!,
                    from = this.toUser,
                    present = null,
                    option = true,
                )
                noticeService.notice(noticeRequest)
            }
        }
    }

    private fun Present.reject() {
        when (type) {
            PresentType.DONATE -> {
                fromUser?.apply { money += cost }
                val noticeRequest = NoticeRequest(
                    noticeType = NoticeType.DONATE_RESPONSE,
                    to = this.fromUser!!,
                    from = this.toUser,
                    present = null,
                    option = false,
                )
                noticeService.notice(noticeRequest)
            }

            else -> null
        }
    }

    @Transactional
    fun presentPoint(
        toUserId: String,
        cost: Long,
    ) {
        val toUser = userService.getUser(toUserId)
        val present = Present(
            toUser = toUser,
            fromUser = null,
            type = PresentType.PRESENT_POINT,
            cost = cost,
        )
        savePresent(present)
    }

    @Transactional
    fun presentMoney(
        toUserId: String,
        cost: Long,
    ) {
        val toUser = userService.getUser(toUserId)
        val present = Present(
            toUser = toUser,
            fromUser = null,
            type = PresentType.PRESENT_MONEY,
            cost = cost,
        )
        savePresent(present)
    }

    @Transactional
    fun donate(
        toUserId: String,
        cost: Long,
    ) {
        val fromUser = userService.getUser()
        if (cost > fromUser.money) throw LARK_MONEY.exception
        val toUser = userService.getUser(toUserId)
        val present = Present(
            toUser = toUser,
            fromUser = fromUser,
            type = PresentType.DONATE,
            cost = cost,
        )
        fromUser.money -= cost
        savePresent(present)
    }

    private fun savePresent(present: Present) {
        presentRepository.save(present)
        val noticeRequest = NoticeRequest(
            noticeType = present.type.noticeType,
            to = present.toUser,
            from = present.fromUser,
            present = present,
        )
        noticeService.notice(noticeRequest)
    }
}