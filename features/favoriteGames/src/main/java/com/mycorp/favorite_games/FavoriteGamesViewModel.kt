package com.mycorp.favorite_games

import androidx.lifecycle.viewModelScope
import androidx.paging.*
import com.mycorp.common.viewModel.BaseViewModel
import com.mycorp.model.FavoriteGameData
import com.mycorp.model.ListItemData
import com.mycorp.myapplication.FavoriteGamesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class FavoriteGamesViewModel(
    private val favoriteGamesRepository: FavoriteGamesRepository
) : BaseViewModel() {
    val favoriteGamesFlow: Flow<PagingData<ListItemData<FavoriteGameData>>> = Pager(
        config = pagingConfig
    ) {
        favoriteGamesRepository.getFavoriteGamesList()
    }.flow
        .map { pagingData ->
            pagingData
                .map {
                    ListItemData(it.id, it.toModel())
                }
        }
        .cachedIn(viewModelScope)

    companion object {
        private val pagingConfig = PagingConfig(
            pageSize = 8,
            enablePlaceholders = false,
            maxSize = 32
        )
    }
}
