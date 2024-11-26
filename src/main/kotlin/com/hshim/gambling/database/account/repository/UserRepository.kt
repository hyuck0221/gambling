package com.hshim.gambling.database.account.repository

import com.hshim.gambling.database.account.User
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository: JpaRepository<User, String> {
}