package com.hshim.gambling.model.account.user

import com.hshim.gambling.database.account.User
import com.hshim.gambling.enums.account.UserType

class UserResponse (
    val id: String,
    val displayName: String,
    val userType: UserType,
    val displayUserType: String,
    val profileUrl: String?,
    val money: Long,
    val borrowedMoney: Long,
    val point: Long,
) {
    constructor(user: User): this(
        id = user.id,
        displayName = user.displayName,
        userType = user.userType,
        displayUserType = user.userType.description,
        profileUrl = user.profileUrl,
        money = user.money,
        borrowedMoney = user.borrowedMoney,
        point = user.point,
    )
}