package com.hshim.gambling.service.account.oauth2

import com.hshim.gambling.database.account.repository.DiscordUserRepository
import com.hshim.gambling.model.account.oauth2.DiscordOauthAttribute
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest
import org.springframework.security.oauth2.core.user.OAuth2User
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class CustomOAuth2UserService(
    private val discordUserRepository: DiscordUserRepository,
) : DefaultOAuth2UserService() {
    @Transactional
    override fun loadUser(userRequest: OAuth2UserRequest): OAuth2User {
        val oAuth2User = super.loadUser(userRequest)
        val attribute = DiscordOauthAttribute(oAuth2User.attributes)
        val user = discordUserRepository.findByDiscordId(attribute.id)
            ?.apply { attribute.updateTo(this) }
            ?: attribute.toEntity()
        discordUserRepository.save(user)
        return oAuth2User
    }
}