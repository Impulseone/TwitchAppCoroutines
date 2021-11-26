package com.mycorp.games

import com.mycorp.favorite_games.FavoriteGamesViewModel
import com.mycorp.games.screens.followers.FollowersViewModel
import com.mycorp.games.screens.game.GameViewModel
import com.mycorp.games.screens.games.GamesViewModel
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