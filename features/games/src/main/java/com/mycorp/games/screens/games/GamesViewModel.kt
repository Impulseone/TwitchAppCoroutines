package com.mycorp.games.screens.games

import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.mycorp.common.viewModel.BaseViewModel
import com.mycorp.games.screens.games.adapter.TopGamesSourceFactory
import com.mycorp.navigation.MainNavigationFlow

class GamesViewModel(
    topGamesSourceFactory: TopGamesSourceFactory
) : BaseViewModel() {

    val gamesFlow = topGamesSourceFactory.create().flow.cachedIn(viewModelScope)

    fun gameItemClicked(gameId: String) {
        navigateTo(
            MainNavigationFlow.GameFlow,
            GamesFragmentDirections.actionGamesFragmentToGameFragment(gameId)
        )
    }
}