package com.mycorp.twitchapprxjava.di

import com.mycorp.twitchapprxjava.api.controllers.GamesController
import com.mycorp.twitchapprxjava.api.controllers.GamesControllerImpl
import org.koin.dsl.module

val networkModule = module {
    single<GamesController> { GamesControllerImpl(get()) }
}