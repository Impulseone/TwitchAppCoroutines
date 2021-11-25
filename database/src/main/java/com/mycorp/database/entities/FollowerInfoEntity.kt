package com.mycorp.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.mycorp.model.FollowerInfo

@Entity
class FollowerInfoEntity(
    @PrimaryKey val id: String,
    val followerName: String,
    val photoUrl: String
) {

    fun toModel() = FollowerInfo(
        followerId = id,
        followerName = followerName,
        photoUrl = photoUrl
    )

    constructor(followerInfo: FollowerInfo):this(
        followerInfo.followerId, followerInfo.followerName, followerInfo.photoUrl
    )
}