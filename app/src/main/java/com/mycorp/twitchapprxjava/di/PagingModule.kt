package com.mycorp.twitchapprxjava.di

import com.mycorp.twitchapprxjava.screens.games.adapter.TopGamesSourceFactory
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val pagingModule = module {
    single { TopGamesSourceFactory(androidContext(), get()) }
}