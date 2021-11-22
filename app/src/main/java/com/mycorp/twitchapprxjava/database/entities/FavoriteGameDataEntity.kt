package com.mycorp.twitchapprxjava.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.mycorp.twitchapprxjava.models.GameData

@Entity
class FavoriteGameDataEntity(
    @PrimaryKey
    val id: String,
    val name: String,
    val logoUrl: String,
    val channelsCount: Int,
    val watchersCount: Int,
) {
    constructor(gameData: GameData) : this(
        id = gameData.id,
        name = gameData.name,
        logoUrl = gameData.logoUrl,
        channelsCount = gameData.channelsCount,
        watchersCount = gameData.watchersCount
    )
}