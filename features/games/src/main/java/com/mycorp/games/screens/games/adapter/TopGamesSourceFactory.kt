package com.mycorp.games.screens.games.adapter

import android.content.Context
import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.mycorp.myapplication.GamesRepository

class TopGamesSourceFactory(private val context: Context, private val gamesRepository: GamesRepository) {
    fun create() = Pager(PagingConfig(pageSize = 10)) {
        TopGamesResponseSource(context, gamesRepository)
    }
}