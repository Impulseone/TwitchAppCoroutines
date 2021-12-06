package com.mycorp.favorite_games

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val favoriteGamesModule = module {
    viewModel<FavoriteGamesViewModel>()
}