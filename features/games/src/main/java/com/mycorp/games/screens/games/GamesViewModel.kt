package com.mycorp.games.screens.games

import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.mycorp.common.viewModel.BaseViewModel
import com.mycorp.games.screens.games.adapter.GameListItem
import com.mycorp.games.screens.games.adapter.TopGamesSourceFactory
import com.mycorp.model.ListItemData
import com.mycorp.navigation.MainNavigationFlow
import kotlinx.coroutines.flow.Flow

class GamesViewModel(
    topGamesSourceFactory: TopGamesSourceFactory
) : BaseViewModel() {

    val gamesFlow = topGamesSourceFactory.create().flow.cachedIn(viewModelScope)

    val gamesFlowDb: Flow<PagingData<ListItemData<GameListItem>>> = topGamesSourceFactory.createDb().cachedIn(viewModelScope)

    fun gameItemClicked(gameId: String) {
        navigateTo(
            MainNavigationFlow.GameFlow,
            GamesFragmentDirections.actionGamesFragmentToGameFragment(gameId)
        )
    }
}