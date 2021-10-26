package com.mycorp.twitchapprxjava.data.storage.model

import com.mycorp.twitchapprxjava.data.storage.room.entities.GameItemDataEntity

class GameItemData(
    val id: String,
    val name: String,
    val photoUrl: String,
    var followersIds: List<String> = mutableListOf(),
    var isLiked: Boolean = false
) {
    companion object {
        fun fromGameData(gameData: GameData, followers: List<FollowerInfo>): GameItemData {
            return GameItemData(
                gameData.id.toString(),
                gameData.name,
                gameData.logoUrl,
                followers.map { it.followerId }
            )
        }

        fun fromGameItemDataEntity(gameItemDataEntity: GameItemDataEntity): GameItemData {
            return GameItemData(
                gameItemDataEntity.id,
                gameItemDataEntity.name,
                gameItemDataEntity.photoUrl,
                gameItemDataEntity.followersIds,
                gameItemDataEntity.isLiked
            )
        }
    }
}