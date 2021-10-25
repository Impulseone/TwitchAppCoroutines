package com.mycorp.twitchapprxjava.data.storage.model

import com.mycorp.twitchapprxjava.data.storage.room.entities.GameDataEntity
import kotlinx.serialization.Serializable

@Serializable
data class GameData(
    val id: Int,
    val name: String,
    val logoUrl: String,
    val channelsCount: Int,
    val watchersCount: Int,
) {
    companion object {
        fun fromEntity(gameDataEntity: GameDataEntity): GameData {
            return GameData(
                gameDataEntity.id,
                gameDataEntity.name,
                gameDataEntity.logoUrl,
                gameDataEntity.channelsCount,
                gameDataEntity.watchersCount
            )
        }
    }
}
