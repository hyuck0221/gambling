package com.hshim.gambling.api.main

import com.hshim.gambling.service.present.PresentService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/main")
class MainController(
    private val presentService: PresentService
) {
    @PostMapping
    fun main() {
        presentService.dailyCheck()
    }
}