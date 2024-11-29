package com.hshim.gambling.api.account.user

import com.hshim.gambling.model.account.user.UserRequest
import com.hshim.gambling.model.account.user.UserResponse
import com.hshim.gambling.service.account.user.UserService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/account/user")
class UserController(private val userService: UserService) {
    @GetMapping("/my-info")
    fun myInfo(): UserResponse {
        return UserResponse(userService.getUser())
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

    @GetMapping("/search")
    fun search(@RequestParam name: String): List<UserResponse> {
        return userService.search(name).map { UserResponse(it) }
    }
}