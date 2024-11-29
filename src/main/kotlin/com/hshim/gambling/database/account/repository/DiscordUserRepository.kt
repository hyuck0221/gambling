package com.hshim.gambling.database.account.repository

import com.hshim.gambling.database.account.DiscordUser
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface DiscordUserRepository: JpaRepository<DiscordUser, String> {
    fun findByDiscordId(discordId: String): DiscordUser?
    fun existsByDiscordId(discordId: String): Boolean
    @Query(
        """
            select du from DiscordUser du 
            where du.id != :userId 
            and (
                du.displayName like concat('%', :name, '%') 
                or du.globalName = concat('%', :name, '%') 
                or du.username = concat('%', :name, '%') 
            )
        """
    )
    fun findAllByNameAndUserIdNot(
        name: String,
        userId: String,
        pageable: Pageable,
    ): Page<DiscordUser>
}