package com.hshim.gambling.api.notice

import com.hshim.gambling.model.notice.NoticeResponse
import com.hshim.gambling.service.notice.NoticeService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/notice")
class NoticeController(private val noticeService: NoticeService) {
    @GetMapping("/list")
    fun noticeInfos(
        @RequestParam(required = false) isRead: Boolean?,
    ): List<NoticeResponse> {
        return noticeService.getNoticeInfos(isRead)
    }

    @PutMapping("/{id}/check")
    fun check(@PathVariable id: String) = noticeService.check(id)
}