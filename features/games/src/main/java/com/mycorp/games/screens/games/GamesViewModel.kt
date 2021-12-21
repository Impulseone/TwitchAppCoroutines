package com.mycorp.games.screens.games

import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.mycorp.common.viewModel.BaseViewModel
import com.mycorp.games.screens.games.adapter.GamesSourceType
import com.mycorp.games.screens.games.adapter.TopGamesSourceFactory
import com.mycorp.navigation.MainNavigationFlow

class GamesViewModel(
    private val topGamesSourceFactory: TopGamesSourceFactory
) : BaseViewModel() {

    fun getFlow(gamesSourceType: GamesSourceType) =
        topGamesSourceFactory.getFlow(gamesSourceType).cachedIn(viewModelScope)

    fun gameItemClicked(gameId: String) {
        navigateTo(
            MainNavigationFlow.GameFlow,
            GamesFragmentDirections.actionGamesFragmentToGameFragment(gameId)
        )
    }
}