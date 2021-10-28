package com.mycorp.twitchapprxjava.data.storage.room.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.mycorp.twitchapprxjava.data.storage.model.GameData

@Entity
data class GameDataEntity(
    @PrimaryKey
    val id: Int,
    val name: String,
    val logoUrl: String,
    val channelsCount: Int,
    val watchersCount: Int
) {
    companion object {
        fun fromGameData(gameData: GameData) =
            GameDataEntity(
                gameData.id,
                gameData.name,
                gameData.logoUrl,
                gameData.channelsCount,
                gameData.watchersCount
            )

    }

}