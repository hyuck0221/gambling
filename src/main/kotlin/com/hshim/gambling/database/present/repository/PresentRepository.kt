package com.hshim.gambling.database.present.repository

import com.hshim.gambling.database.present.Present
import org.springframework.data.jpa.repository.JpaRepository

interface PresentRepository : JpaRepository<Present, String> {
}