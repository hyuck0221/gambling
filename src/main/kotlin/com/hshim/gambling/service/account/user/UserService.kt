package com.hshim.gambling.service.account.user

import com.hshim.gambling.database.account.DiscordUser
import com.hshim.gambling.database.account.User
import com.hshim.gambling.database.account.repository.DiscordUserRepository
import com.hshim.gambling.database.account.repository.UserRepository
import com.hshim.gambling.exception.GlobalException
import com.hshim.gambling.model.account.user.UserRequest
import com.hshim.gambling.model.account.user.UserResponse
import com.hshim.gambling.service.account.oauth2.CustomOAuth2UserService
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class UserService(
    private val userRepository: UserRepository,
    private val discordUserRepository: DiscordUserRepository,
    private val customOAuth2UserService: CustomOAuth2UserService,
) {
    @Transactional(readOnly = true)
    fun getUser(id: String): User {
        val user = userRepository.findByIdOrNull(id)
        when {
            user == null -> throw GlobalException.NOT_FOUND_USER.exception
            user.disable -> throw GlobalException.IS_DISABLED_USER.exception
        }
        return user!!
    }

    @Transactional(readOnly = true)
    fun getUserByDiscordId(discordId: String): User {
        val discordUser = discordUserRepository.findByDiscordId(discordId)
            ?: throw GlobalException.NOT_FOUND_USER.exception
        return getUser(discordUser.id)
    }

    @Transactional(readOnly = true)
    fun getUser(): User {
        val attribute = customOAuth2UserService.getDiscordAttribute()
        return getUserByDiscordId(attribute.id)
    }

    @Transactional(readOnly = true)
    fun search(name: String) = discordUserRepository.findAllByName(name)

    @Transactional(readOnly = true)
    fun getInfo(id: String): UserResponse {
        val user = getUser(id)
        return UserResponse(user)
    }

    @Transactional
    fun update(
        id: String,
        req: UserRequest,
    ): UserResponse {
        val user = getUser(id)
        req.updateTo(user)
        return UserResponse(user)
    }

    @Transactional
    fun synchronization(id: String): UserResponse {
        val user = getUser(id)
        val attribute = customOAuth2UserService.getDiscordAttribute()
        when (user) {
            is DiscordUser -> attribute.updateTo(user)
        }
        return UserResponse(user)
    }
}