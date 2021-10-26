package com.mycorp.twitchapprxjava.data.storage.room.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.mycorp.twitchapprxjava.data.storage.model.GameItemData

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
        fun fromGameItemData(gameItemData: GameItemData): GameItemDataEntity {
            return GameItemDataEntity(
                gameItemData.id,
                gameItemData.name,
                gameItemData.photoUrl,
                gameItemData.followersIds,
                gameItemData.isLiked
            )
        }
    }
}