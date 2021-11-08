package com.mycorp.twitchapprxjava.screens.games.adapter

import android.content.Context
import com.mycorp.twitchapprxjava.R
import com.mycorp.twitchapprxjava.database.model.GameData
import java.util.*

data class GameListItem(
    val id: String,
    val name: String,
    val logoUrl: String,
    val channels: String,
    val watchers: String
) {
    constructor(context: Context, game: GameData) : this(
        id = game.id,
        name = game.name,
        logoUrl = game.logoUrl,
        channels = context.getString(R.string.channels, game.channelsCount.toString()),
        watchers = context.getString(R.string.watchers, game.watchersCount.toString())
    )
}