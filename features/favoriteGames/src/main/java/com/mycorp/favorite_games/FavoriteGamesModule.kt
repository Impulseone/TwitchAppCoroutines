package com.mycorp.favorite_games

import com.mycorp.favorite_games.adapter.FavoriteGamesSourceFactory
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val favoriteGamesModule = module {
    single { FavoriteGamesSourceFactory(get()) }
    viewModel<FavoriteGamesViewModel>()
}