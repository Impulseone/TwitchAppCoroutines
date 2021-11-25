package com.mycorp.features

import com.mycorp.twitchapprxjava.screens.favoriteGames.adapter.FavoriteGamesSourceFactory
import com.mycorp.twitchapprxjava.screens.games.adapter.TopGamesSourceFactory
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val pagingModule = module {
    single { TopGamesSourceFactory(androidContext(), get()) }
    single { FavoriteGamesSourceFactory(get()) }
}