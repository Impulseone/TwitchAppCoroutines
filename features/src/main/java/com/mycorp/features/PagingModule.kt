package com.mycorp.features

import com.mycorp.features.screens.favoriteGames.adapter.FavoriteGamesSourceFactory
import com.mycorp.features.screens.games.adapter.DbGamesSourceFactory
import com.mycorp.features.screens.games.adapter.TopGamesSourceFactory
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val pagingModule = module {
    single { TopGamesSourceFactory(androidContext(), get()) }
    single { DbGamesSourceFactory(androidContext(), get()) }
    single { FavoriteGamesSourceFactory(get()) }
}