package com.mycorp.twitchapprxjava.data.storage.room.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class FollowerInfoEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val followerName: String,
    val photoUrl: String
)