package com.mycorp.twitchapprxjava.models

import com.mycorp.twitchapprxjava.database.entities.FavoriteGameDataEntity

class FavoriteGameData(
    val id: String,
    val name: String,
    val logoUrl: String,
    val channelsCount: Int,
    val watchersCount: Int,
) {
    constructor(gameData: FavoriteGameDataEntity) : this(
        id = gameData.id,
        name = gameData.name,
        logoUrl = gameData.logoUrl,
        channelsCount = gameData.channelsCount,
        watchersCount = gameData.watchersCount
    )
}