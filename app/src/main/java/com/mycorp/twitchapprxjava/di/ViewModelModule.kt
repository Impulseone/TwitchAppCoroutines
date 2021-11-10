package com.mycorp.twitchapprxjava.di

import com.mycorp.twitchapprxjava.screens.favoriteGames.FavoriteGamesVM
import com.mycorp.twitchapprxjava.screens.followers.FollowersVM
import com.mycorp.twitchapprxjava.screens.rating.RatingVM
import com.mycorp.twitchapprxjava.screens.game.SingleGameDataVM
import com.mycorp.twitchapprxjava.screens.games.GamesVM
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel<GamesVM>()
    viewModel<RatingVM>()
    viewModel<SingleGameDataVM>()
    viewModel<FollowersVM>()
    viewModel<FavoriteGamesVM>()
}