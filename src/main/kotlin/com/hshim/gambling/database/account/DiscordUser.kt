package com.hshim.gambling.database.account

import com.hshim.gambling.enums.account.DiscordPremiumType
import com.hshim.gambling.enums.account.UserType
import jakarta.persistence.*

@Entity
@Table(name = "discord_user")
@DiscriminatorValue(value = "DISCORD")
class DiscordUser(
    @Column(nullable = false, columnDefinition = "varchar(100)")
    val discordId: String,

    @Column(nullable = false)
    var username: String,

    @Column(nullable = false)
    var globalName: String,

    @Column(nullable = true)
    var avatar: String?,

    @Column(nullable = true)
    var banner: String?,

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    var premiumType: DiscordPremiumType,
) : User(
    displayName = globalName,
    userType = UserType.DISCORD,
) {
    fun getAvatarUrl() = "https://cdn.discordapp.com/avatars/$discordId/$avatar"
}