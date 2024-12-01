package com.hshim.gambling.database.present

import com.hshim.gambling.database.account.User
import com.hshim.gambling.database.base.BaseTimeEntity
import com.hshim.gambling.enums.present.PresentStatus
import com.hshim.gambling.enums.present.PresentType
import io.autocrypt.sakarinblue.universe.util.CommonUtil.uuid
import jakarta.persistence.*


@Entity
@Table(name = "present")
class Present(
    @Id
    @Column(nullable = false, columnDefinition = "char(32)")
    var id: String = uuid(),

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "to_user_id", nullable = false, unique = false)
    val toUser: User,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "from_user_id", nullable = true, unique = false)
    val fromUser: User?,

    @Column(nullable = false, columnDefinition = "varchar(255)")
    @Enumerated(EnumType.STRING)
    var status: PresentStatus = PresentStatus.WAIT,

    @Column(nullable = false, columnDefinition = "varchar(255)")
    @Enumerated(EnumType.STRING)
    var type: PresentType,

    @Column(nullable = false)
    val cost: Long,

) : BaseTimeEntity()