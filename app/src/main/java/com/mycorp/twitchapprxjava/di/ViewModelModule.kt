package com.mycorp.twitchapprxjava.di

import com.mycorp.twitchapprxjava.screens.favoriteGames.FavoriteGamesViewModel
import com.mycorp.twitchapprxjava.screens.followers.FollowersViewModel
import com.mycorp.twitchapprxjava.screens.rating.RatingViewModel
import com.mycorp.twitchapprxjava.screens.game.GameViewModel
import com.mycorp.twitchapprxjava.screens.games.GamesViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel<GamesViewModel>()
    viewModel<RatingViewModel>()
    viewModel<GameViewModel>()
    viewModel<FollowersViewModel>()
    viewModel<FavoriteGamesViewModel>()
}