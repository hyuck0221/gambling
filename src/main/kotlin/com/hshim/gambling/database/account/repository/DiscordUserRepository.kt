package com.hshim.gambling.database.account.repository

import com.hshim.gambling.database.account.DiscordUser
import org.springframework.data.jpa.repository.JpaRepository

interface DiscordUserRepository: JpaRepository<DiscordUser, String> {
    fun findByDiscordId(discordId: String): DiscordUser?
    fun existsByDiscordId(discordId: String): Boolean
}