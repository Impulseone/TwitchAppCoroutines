package com.mycorp.games.screens.games.adapter

import android.content.Context
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.mycorp.model.ListItemData
import com.mycorp.myapplication.GamesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

enum class GamesSourceType {
    SERVER,
    DATABASE
}

class TopGamesSourceFactory(
    private val context: Context,
    private val gamesRepository: GamesRepository
) {

    fun getFlow(gamesSourceType: GamesSourceType): Flow<PagingData<ListItemData<GameListItem>>> {
        return when (gamesSourceType) {
            GamesSourceType.SERVER -> createServerFlow()
            GamesSourceType.DATABASE -> createDbFlow()
        }
    }

    private fun createServerFlow() = Pager(PagingConfig(pageSize = 10, initialLoadSize = 10)) {
        TopGamesResponseSource(context, gamesRepository)
    }.flow

    private fun createDbFlow() = Pager(
        config = PagingConfig(pageSize = 10, initialLoadSize = 10)
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