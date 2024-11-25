package com.hshim.gambling.controller

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping

@Controller
class MainController {
    @GetMapping("/login")
    fun login(): String {
        return "login.html"
    }
}