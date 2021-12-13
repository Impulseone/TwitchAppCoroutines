package com.mycorp.games.screens.games

import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.mycorp.common.Data
import com.mycorp.common.PagedListState
import com.mycorp.common.viewModel.BaseViewModel
import com.mycorp.games.screens.games.adapter.GameListItem
import com.mycorp.games.screens.games.adapter.TopGamesSourceFactory
import com.mycorp.navigation.MainNavigationFlow

class GamesViewModel(
    topGamesSourceFactory: TopGamesSourceFactory
) : BaseViewModel() {

    val gamesFlow = topGamesSourceFactory.create().flow.cachedIn(viewModelScope)

    val pagedGamesLiveData = Data<PagedListState<GameListItem>>()

    fun gameItemClicked(position: Int) {
        pagedGamesLiveData.value?.data?.get(position)?.id?.let {
            navigateTo(MainNavigationFlow.GameFlow, GamesFragmentDirections.actionGamesFragmentToGameFragment(it))
        }
    }
}