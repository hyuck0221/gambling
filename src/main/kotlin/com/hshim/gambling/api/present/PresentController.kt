package com.hshim.gambling.api.present

import com.hshim.gambling.enums.present.PresentStatus
import com.hshim.gambling.service.present.PresentService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/present")
class PresentController(private val presentService: PresentService) {

    @PutMapping("/{id}")
    fun update(
        @PathVariable id: String,
        @RequestParam(required = true) status: PresentStatus,
    ) = presentService.update(id, status)

    @PostMapping("/point")
    fun point(
        @RequestParam(required = true) toUserId: String,
        @RequestParam(required = true) cost: Long,
    ) = presentService.presentPoint(toUserId, cost)

    @PostMapping("/money")
    fun money(
        @RequestParam(required = true) toUserId: String,
        @RequestParam(required = true) cost: Long,
    ) = presentService.presentMoney(toUserId, cost)

    @PostMapping("/donate")
    fun donate(
        @RequestParam(required = true) toUserId: String,
        @RequestParam(required = true) cost: Long,
    ) = presentService.donate(toUserId, cost)
}