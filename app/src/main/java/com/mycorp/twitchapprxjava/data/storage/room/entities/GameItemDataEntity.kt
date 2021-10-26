package com.mycorp.twitchapprxjava.data.storage.room.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.mycorp.twitchapprxjava.data.storage.model.SingleGameData

@Entity
class GameItemDataEntity(
    @PrimaryKey
    val id: String,
    val name: String,
    val photoUrl: String,
    var followersIds: List<String> = mutableListOf(),
    var isLiked: Boolean = false
) {
    companion object {
        fun fromGameItemData(singleGameData: SingleGameData): GameItemDataEntity {
            return GameItemDataEntity(
                singleGameData.id,
                singleGameData.name,
                singleGameData.photoUrl,
                singleGameData.followersIds,
                singleGameData.isLiked
            )
        }
    }
}