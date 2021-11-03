package com.mycorp.twitchapprxjava.di

import com.mycorp.twitchapprxjava.presentation.viewModel.FavoriteGamesVM
import com.mycorp.twitchapprxjava.presentation.viewModel.FollowersListVM
import com.mycorp.twitchapprxjava.presentation.viewModel.RatingVM
import com.mycorp.twitchapprxjava.presentation.viewModel.SingleGameDataVM
import com.mycorp.twitchapprxjava.screens.games.GamesVM
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel<GamesVM>()
    viewModel<RatingVM>()
    viewModel<SingleGameDataVM>()
    viewModel<FollowersListVM>()
    viewModel<FavoriteGamesVM>()
}