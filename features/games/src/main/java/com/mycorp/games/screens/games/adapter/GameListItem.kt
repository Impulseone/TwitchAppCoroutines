package com.mycorp.games.screens.games.adapter

import android.content.Context
import com.mycorp.games.R
import com.mycorp.model.GameData

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
        channels = context.getString(R.string.scr_game_channels, game.channelsCount.toString()),
        watchers = context.getString(R.string.scr_game_watchers, game.watchersCount.toString())
    )
}