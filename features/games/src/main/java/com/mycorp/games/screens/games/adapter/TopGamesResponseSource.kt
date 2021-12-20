package com.mycorp.games.screens.games.adapter

import android.content.Context
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.mycorp.model.ListItemData
import com.mycorp.myapplication.GamesRepository

class TopGamesResponseSource(
    private val context: Context,
    private val gamesRepository: GamesRepository
) : PagingSource<Int, ListItemData<GameListItem>>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ListItemData<GameListItem>> {
        try {
            val currentLoadingPageKey = params.key ?: defaultKey
            val gamesList =
                gamesRepository.fetchGamesDataList(
                    params.loadSize,
                    currentLoadingPageKey * keyParam
                )
            gamesRepository.insertGamesDataSuspend(gamesList)
            val gameItemsList = gamesList.map {
                ListItemData(it.id, GameListItem(context, it))
            }
            val responseData = mutableListOf<ListItemData<GameListItem>>()
            responseData.addAll(gameItemsList)

            val prevKey =
                if (currentLoadingPageKey == defaultKey) null else currentLoadingPageKey - 1

            return LoadResult.Page(
                data = responseData,
                prevKey = prevKey,
                nextKey = currentLoadingPageKey.plus(1)
            )
        } catch (e: Exception) {
            return LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, ListItemData<GameListItem>>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    companion object KeyParam {
        private const val defaultKey = 0
        private const val keyParam = 10
    }
}