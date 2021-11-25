package com.mycorp.api

import com.mycorp.twitchapprxjava.api.controllers.FollowersController
import com.mycorp.twitchapprxjava.api.controllers.FollowersControllerImplementation
import com.mycorp.twitchapprxjava.api.controllers.GamesController
import com.mycorp.twitchapprxjava.api.controllers.GamesControllerImpl
import org.koin.dsl.module

val networkModule = module {
    single<GamesController> { GamesControllerImpl(get()) }
    single<FollowersController> { FollowersControllerImplementation(get()) }
}