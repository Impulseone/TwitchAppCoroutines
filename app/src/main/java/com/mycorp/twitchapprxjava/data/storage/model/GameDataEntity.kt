package com.mycorp.twitchapprxjava.data.storage.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class GameDataEntity(
    @PrimaryKey
    val id: Int,
    val name: String,
    val logoUrl: String,
    val channelsCount: Int,
    val watchersCount: Int
) {
    fun toGameData(): GameData {
        return GameData(
            id,
            name,
            logoUrl,
            channelsCount,
            watchersCount
        )
    }

    companion object {
        fun toGameDataEntity(gameData: GameData): GameDataEntity {
            return GameDataEntity(
                gameData.id,
                gameData.name,
                gameData.logoUrl,
                gameData.channelsCount,
                gameData.watchersCount
            )
        }
    }

}