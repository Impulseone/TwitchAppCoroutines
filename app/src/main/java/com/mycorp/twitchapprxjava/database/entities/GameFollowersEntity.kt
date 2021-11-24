package com.mycorp.twitchapprxjava.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.mycorp.model.FollowerInfo

@Entity
class GameFollowersEntity(
    @PrimaryKey
    val gameId: String,
    val followersId: List<String>
) {
    constructor(followersInfo: List<FollowerInfo>, gameId: String) : this(
        gameId = gameId,
        followersId = followersInfo.map { it.followerId }
    )
}