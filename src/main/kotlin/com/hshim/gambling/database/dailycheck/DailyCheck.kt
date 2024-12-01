package com.hshim.gambling.database.dailycheck

import com.hshim.gambling.database.account.User
import com.hshim.gambling.database.base.BaseTimeEntity
import com.hshim.gambling.database.dailycheck.converter.DailyCheckTypeConverter
import com.hshim.gambling.enums.dailycheck.DailyCheckType
import io.autocrypt.sakarinblue.universe.util.CommonUtil.uuid
import jakarta.persistence.*

@Entity
@Table(name = "daily_check")
class DailyCheck(
    @Id
    @Column(nullable = false, columnDefinition = "char(32)")
    var id: String = uuid(),

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false, unique = false)
    val user: User,

    @Column(nullable = false, columnDefinition = "varchar(255)")
    @Convert(converter = DailyCheckTypeConverter::class)
    val types: List<DailyCheckType>,

    @Column(nullable = false)
    val cost: Long = types.sumOf { it.cost }
) : BaseTimeEntity()