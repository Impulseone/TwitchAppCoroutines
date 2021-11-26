package com.mycorp.features

import com.mycorp.features.screens.favoriteGames.FavoriteGamesViewModel
import com.mycorp.features.screens.followers.FollowersViewModel
import com.mycorp.features.screens.game.GameViewModel
import com.mycorp.features.screens.games.GamesViewModel
import com.mycorp.rating.RatingViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel<GamesViewModel>()
    viewModel<RatingViewModel>()
    viewModel<GameViewModel>()
    viewModel<FollowersViewModel>()
    viewModel<FavoriteGamesViewModel>()
}