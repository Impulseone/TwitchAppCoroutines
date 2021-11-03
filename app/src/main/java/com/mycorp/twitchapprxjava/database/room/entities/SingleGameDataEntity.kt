package com.mycorp.twitchapprxjava.database.room.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.mycorp.twitchapprxjava.database.model.SingleGameData

@Entity
class SingleGameDataEntity(
    @PrimaryKey
    val id: String,
    val name: String,
    val photoUrl: String,
    var followersIds: List<String> = mutableListOf(),
    var isLiked: Boolean = false
) {
    companion object {
        fun fromSingleGameData(singleGameData: SingleGameData): SingleGameDataEntity {
            return SingleGameDataEntity(
                singleGameData.id,
                singleGameData.name,
                singleGameData.photoUrl,
                singleGameData.followersIds,
                singleGameData.isLiked
            )
        }
    }
}