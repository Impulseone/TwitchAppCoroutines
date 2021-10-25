package com.mycorp.twitchapprxjava.data.storage.room.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.mycorp.twitchapprxjava.data.storage.model.FollowerInfo

@Entity
class FollowerInfoEntity(
    val followerName: String,
    val photoUrl: String
){
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null

    companion object{
        fun fromFollowerInfo(followerInfo:FollowerInfo) = FollowerInfoEntity(
           followerInfo.followerName, followerInfo.photoUrl
        )
    }
}