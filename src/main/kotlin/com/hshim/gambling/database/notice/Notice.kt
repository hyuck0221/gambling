package com.hshim.gambling.database.notice

import com.hshim.gambling.database.account.User
import com.hshim.gambling.database.base.BaseTimeEntity
import com.hshim.gambling.database.present.Present
import com.hshim.gambling.enums.notice.NoticeType
import io.autocrypt.sakarinblue.universe.util.CommonUtil.uuid
import jakarta.persistence.*


@Entity
@Table(name = "notice")
class Notice(
    @Id
    @Column(nullable = false, columnDefinition = "char(32)")
    var id: String = uuid(),

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false, unique = false)
    val user: User,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "present_id", nullable = true, unique = false)
    val present: Present?,

    @Column(nullable = false, columnDefinition = "varchar(255)")
    @Enumerated(EnumType.STRING)
    val noticeType: NoticeType,

    @Column(nullable = false)
    var isRead: Boolean = false,

    @Column(nullable = false)
    val description: String,
) : BaseTimeEntity() {
    fun read() {
        isRead = true
    }
}