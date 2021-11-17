package com.mycorp.twitchapprxjava.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.mycorp.twitchapprxjava.models.GameData

@Entity
data class GameDataEntity(
    @PrimaryKey
    val id: String,
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