package com.hshim.gambling.model.account.user

import com.hshim.gambling.database.account.User

class UserRequest (
    val displayName: String,
    val profileUrl: String?,
) {
    fun updateTo(user: User) {
        user.displayName = displayName
        user.profileUrl = profileUrl
    }
}