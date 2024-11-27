package com.hshim.gambling.api.account.user

import com.hshim.gambling.model.account.user.UserRequest
import com.hshim.gambling.model.account.user.UserResponse
import com.hshim.gambling.service.account.oauth2.CustomOAuth2UserService
import com.hshim.gambling.service.account.user.UserService
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.oauth2.core.user.OAuth2User
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/account/user")
class UserController(
    private val userService: UserService,
    private val customOAuth2UserService: CustomOAuth2UserService,
) {
    @GetMapping("/my-info")
    fun myInfo(): UserResponse {
        val authentication = SecurityContextHolder.getContext().authentication.principal as OAuth2User
        val attribute = customOAuth2UserService.getDiscordAttribute(authentication)
        return userService.getInfoByDiscordId(attribute.id)
    }

    @GetMapping("/{id}")
    fun info(@PathVariable id: String): UserResponse {
        return userService.getInfo(id)
    }

    @PutMapping("/{id}")
    fun update(
        @PathVariable id: String,
        @RequestBody req: UserRequest,
    ): UserResponse {
        return userService.update(id, req)
    }

    @PutMapping("/{id}/synchronization")
    fun synchronization(@PathVariable id: String): UserResponse {
        return userService.synchronization(id)
    }
}