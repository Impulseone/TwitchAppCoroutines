package com.mycorp.games

import androidx.paging.ExperimentalPagingApi
import com.mycorp.games.screens.followers.FollowersViewModel
import com.mycorp.games.screens.game.GameViewModel
import com.mycorp.games.screens.games.GamesViewModel
import com.mycorp.games.screens.games.adapter.TopGamesSourceFactory
import kotlinx.coroutines.Dispatchers
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

@OptIn(ExperimentalPagingApi::class)
val gamesModule = module {
    single<GameDataInfoUseCase> { GameDataInfoUseCaseImpl(get(), get(), get(), get()) }
    single { Dispatchers.IO }
    single { TopGamesSourceFactory(androidContext(), get()) }
    viewModel<GamesViewModel>()
    viewModel<GameViewModel>()
    viewModel<FollowersViewModel>()
}