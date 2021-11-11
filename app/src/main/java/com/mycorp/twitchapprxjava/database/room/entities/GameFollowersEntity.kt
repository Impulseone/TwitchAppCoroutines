package com.mycorp.twitchapprxjava.database.room.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class GameFollowersEntity(
    @PrimaryKey
    val gameId: String,
    val followersId: List<String>
) {

}