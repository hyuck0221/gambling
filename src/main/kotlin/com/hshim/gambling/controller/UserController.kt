package com.hshim.gambling.controller

import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import java.security.Principal

@RestController
class UserController {

    @GetMapping("/account/oauth/discord")
    fun profile(principal: Principal) {
        println("Authenticated Principal: $principal")
    }
}