package com.hshim.gambling.database.account.repository

import com.hshim.gambling.database.account.DiscordUser
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface DiscordUserRepository: JpaRepository<DiscordUser, String> {
    fun findByDiscordId(discordId: String): DiscordUser?
    fun existsByDiscordId(discordId: String): Boolean
    @Query(
        """
            select du from DiscordUser du 
            where du.displayName = :name 
            or du.globalName = :name 
            or du.username = :name
        """
    )
    fun findAllByName(name: String): List<DiscordUser>
}