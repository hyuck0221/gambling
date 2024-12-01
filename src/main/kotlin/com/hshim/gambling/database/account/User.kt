package com.hshim.gambling.database.account

import com.hshim.gambling.database.base.BaseTimeEntity
import com.hshim.gambling.enums.account.UserType
import com.hshim.gambling.enums.game.GameMode
import io.autocrypt.sakarinblue.universe.util.CommonUtil.uuid
import jakarta.persistence.*


@Entity
@Table(name = "user")
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "user_type")
class User(
    @Id
    @Column(nullable = false, columnDefinition = "char(32)")
    var id: String = uuid(),

    @Column(
        nullable = false,
        name = "user_type",
        insertable = false,
        updatable = false,
        columnDefinition = "varchar(15)"
    )
    @Enumerated(EnumType.STRING)
    open var userType: UserType,

    @Column(nullable = false)
    var displayName: String,

    @Column(nullable = false)
    var disable: Boolean = false,

    @Column(nullable = true)
    var profileUrl: String?,

    @Column(nullable = false)
    var money: Long = 5000,

    @Column(nullable = false)
    var borrowedMoney: Long = 0,

    @Column(nullable = false)
    var point: Long = 0,
) : BaseTimeEntity() {
    fun addMoney(cost: Long) {
        money += cost
    }

    fun minusMoney(cost: Long) {
        money -= cost
        if (money < 0) {
            borrowedMoney -= money * -1
            money = 0
        }
    }

    fun lose(
        gameMode: GameMode,
        cost: Long,
    ) {
        when {
            gameMode.onlyPoint -> point -= cost
            gameMode.canUsePoint -> {
                point -= cost
                if (point < 0) {
                    val remainingCost = point * -1
                    minusMoney(remainingCost)
                    point = 0
                }
            }
            else -> minusMoney(cost)
        }
    }
}