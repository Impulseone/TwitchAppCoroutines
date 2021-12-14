package com.mycorp.games.screens.games

import androidx.lifecycle.viewModelScope
import androidx.paging.*
import com.mycorp.common.viewModel.BaseViewModel
import com.mycorp.database.entities.GameDataEntity
import com.mycorp.database.storage.RemoteKeysStorage
import com.mycorp.games.screens.games.adapter.TopGamesRemoteMediator
import com.mycorp.myapplication.GamesRepository
import com.mycorp.navigation.MainNavigationFlow
import kotlinx.coroutines.flow.Flow

class GamesViewModel(
    private val gamesRepository: GamesRepository,
    private val remoteKeysStorage: RemoteKeysStorage
) : BaseViewModel() {

    fun gameItemClicked(gameId: String) {
        navigateTo(
            MainNavigationFlow.GameFlow,
            GamesFragmentDirections.actionGamesFragmentToGameFragment(gameId)
        )
    }

    @OptIn(ExperimentalPagingApi::class)
    fun getGames(): Flow<PagingData<GameDataEntity>> {
        val pagingSourceFactory = { gamesRepository.getGamesPaging() }
        return Pager(
            config = PagingConfig(
                pageSize = 10,
                prefetchDistance = 6,
                enablePlaceholders = false,
                initialLoadSize = 20
            ),
            pagingSourceFactory = pagingSourceFactory,
            remoteMediator = TopGamesRemoteMediator(gamesRepository, remoteKeysStorage)
        ).flow.cachedIn(viewModelScope)
    }
}