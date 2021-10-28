package com.mycorp.twitchapprxjava.presentation.viewModel

import com.mycorp.twitchapprxjava.domain.use_cases.GetFromDbUseCase

class FavoriteGamesVM(private val getFromDbUseCase: GetFromDbUseCase) : BaseViewModel() {
    fun getGames() = getFromDbUseCase.getPagedFavoriteGames()
}
