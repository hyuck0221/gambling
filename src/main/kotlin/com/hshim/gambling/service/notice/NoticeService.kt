package com.hshim.gambling.service.notice

import com.hshim.gambling.database.notice.Notice
import com.hshim.gambling.database.notice.repository.NoticeRepository
import com.hshim.gambling.database.present.Present
import com.hshim.gambling.exception.GlobalException
import com.hshim.gambling.model.notice.NoticeRequest
import com.hshim.gambling.model.notice.NoticeResponse
import com.hshim.gambling.model.websocket.notice.NoticeEventModel
import com.hshim.gambling.service.account.user.UserService
import com.hshim.gambling.setting.websocket.SessionManager
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class NoticeService(
    private val userService: UserService,
    private val noticeRepository: NoticeRepository,
    private val sessionManager: SessionManager,
) {
    @Transactional(readOnly = true)
    fun getNotice(id: String): Notice {
        return noticeRepository.findByIdOrNull(id)
            ?: throw GlobalException.NOT_FOUND_NOTICE.exception
    }

    @Transactional(readOnly = true)
    fun getNotice(present: Present): Notice {
        return noticeRepository.findByPresent(present)
            ?: throw GlobalException.NOT_FOUND_NOTICE.exception
    }

    @Transactional(readOnly = true)
    fun getNoticeInfos(isRead: Boolean?): List<NoticeResponse> {
        val user = userService.getUser()
        val notices = when (isRead) {
            null -> noticeRepository.findAllByUserOrderByCreateDateDesc(user)
            else -> noticeRepository.findAllByUserAndIsReadOrderByCreateDateDesc(user, isRead)
        }
        return notices.map { NoticeResponse(it) }
    }

    @Transactional
    fun check(id: String) = getNotice(id).read()

    @Transactional
    fun check(present: Present) = getNotice(present).read()

    @Transactional
    fun notice(req: NoticeRequest) {
        val notice = noticeRepository.save(req.toEntity())
        sessionManager.send(notice.user.id, NoticeEventModel.NoticeInfo(notice))
    }
}