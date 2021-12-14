package com.mycorp.games.screens.games

import androidx.lifecycle.viewModelScope
import androidx.paging.*
import com.mycorp.common.viewModel.BaseViewModel
import com.mycorp.database.entities.GameDataEntity
import com.mycorp.games.screens.games.adapter.TopGamesRemoteMediator
import com.mycorp.myapplication.GamesRepository
import com.mycorp.navigation.MainNavigationFlow
import kotlinx.coroutines.flow.Flow

class GamesViewModel(private val gamesRepository: GamesRepository) : BaseViewModel() {

    val gamesFlow = getGames().cachedIn(viewModelScope)

    fun gameItemClicked(gameId: String) {
        navigateTo(
            MainNavigationFlow.GameFlow,
            GamesFragmentDirections.actionGamesFragmentToGameFragment(gameId)
        )
    }

    @OptIn(ExperimentalPagingApi::class)
    private fun getGames(): Flow<PagingData<GameDataEntity>> {
        val pagingSourceFactory = { gamesRepository.getGamesPaging() }
        return Pager(
            config = PagingConfig(pageSize = 10),
            pagingSourceFactory = pagingSourceFactory,
            remoteMediator = TopGamesRemoteMediator(gamesRepository)
        ).flow
    }
}