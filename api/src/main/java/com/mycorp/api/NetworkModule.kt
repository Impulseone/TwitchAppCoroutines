package com.mycorp.api

import com.mycorp.api.controllers.FollowersController
import com.mycorp.api.controllers.FollowersControllerImplementation
import com.mycorp.api.controllers.GamesController
import com.mycorp.api.controllers.GamesControllerImpl
import org.koin.dsl.module

val networkModule = module {
    single<GamesController> { GamesControllerImpl(get()) }
    single<FollowersController> { FollowersControllerImplementation(get()) }
}