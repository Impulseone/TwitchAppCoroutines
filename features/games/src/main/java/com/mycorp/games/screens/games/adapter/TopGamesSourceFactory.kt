package com.mycorp.games.screens.games.adapter

import android.content.Context
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.map
import com.mycorp.model.ListItemData
import com.mycorp.myapplication.GamesRepository
import kotlinx.coroutines.flow.map

class TopGamesSourceFactory(
    private val context: Context,
    private val gamesRepository: GamesRepository
) {
    fun create() = Pager(PagingConfig(pageSize = 10)) {
        TopGamesResponseSource(context, gamesRepository)
    }

    fun createDb() = Pager(
        config = PagingConfig(pageSize = 10)
    ) {
        gamesRepository.getGamesPaging()
    }.flow
        .map { pagingData ->
            pagingData
                .map {
                    ListItemData(it.id, GameListItem(context, it.toModel()))
                }
        }
}