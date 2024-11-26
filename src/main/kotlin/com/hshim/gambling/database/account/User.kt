package com.hshim.gambling.database.account

import com.hshim.gambling.database.base.BaseTimeEntity
import com.hshim.gambling.enums.account.UserType
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
) : BaseTimeEntity()