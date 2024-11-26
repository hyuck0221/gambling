package com.hshim.gambling.controller

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping

@Controller
class LoginController {
    @GetMapping("/login")
    fun login(): String {
        return "login.html"
    }
    @GetMapping("/main")
    fun main(): String {
        return "index.html"
    }
}