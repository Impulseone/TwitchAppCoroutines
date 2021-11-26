package com.mycorp.games

import com.mycorp.games.screens.games.adapter.DbGamesSourceFactory
import com.mycorp.games.screens.games.adapter.TopGamesSourceFactory
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val pagingModule = module {
    single { TopGamesSourceFactory(androidContext(), get()) }
    single { DbGamesSourceFactory(androidContext(), get()) }
}