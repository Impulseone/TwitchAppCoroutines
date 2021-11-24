package com.mycorp.twitchapprxjava.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.mycorp.model.GameData

@Entity
data class GameDataEntity(
    @PrimaryKey
    val id: String,
    val name: String,
    val logoUrl: String,
    val channelsCount: Int,
    val watchersCount: Int
) {

    constructor(gameData: GameData):this(
        gameData.id,
        gameData.name,
        gameData.logoUrl,
        gameData.channelsCount,
        gameData.watchersCount
    )

    fun toModel() = GameData(
        id, name, logoUrl, channelsCount, watchersCount
    )

}