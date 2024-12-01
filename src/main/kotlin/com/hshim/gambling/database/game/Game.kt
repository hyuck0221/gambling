package com.hshim.gambling.database.game

import com.hshim.gambling.database.account.User
import com.hshim.gambling.database.base.BaseTimeEntity
import com.hshim.gambling.enums.game.GameMode
import io.autocrypt.sakarinblue.universe.util.CommonUtil.uuid
import jakarta.persistence.*


@Entity
@Table(name = "game")
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "game_mode")
class Game(
    @Id
    @Column(nullable = false, columnDefinition = "char(32)")
    var id: String = uuid(),

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false, unique = false)
    val user: User,

    @Column(nullable = false)
    val isWin: Boolean,

    @Column(nullable = false)
    val cost: Long,

    @Column(
        nullable = false,
        name = "game_mode",
        insertable = false,
        updatable = false,
        columnDefinition = "varchar(255)",
    )
    @Enumerated(EnumType.STRING)
    open var gameMode: GameMode,
) : BaseTimeEntity()