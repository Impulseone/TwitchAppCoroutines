package com.mycorp.games

import com.mycorp.games.screens.followers.FollowersViewModel
import com.mycorp.games.screens.game.GameViewModel
import com.mycorp.games.screens.games.GamesViewModel
import com.mycorp.games.screens.games.adapter.DbGamesSourceFactory
import com.mycorp.games.screens.games.adapter.TopGamesSourceFactory
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val gamesModule = module {
    single { TopGamesSourceFactory(androidContext(), get()) }
    single { DbGamesSourceFactory(androidContext(), get()) }
    single<GameDataUseCase> { GameDataUseCaseImpl(get(), get(), get()) }
    viewModel<GamesViewModel>()
    viewModel<GameViewModel>()
    viewModel<FollowersViewModel>()
}