package com.mycorp.twitchapprxjava.di

import com.mycorp.twitchapprxjava.api.controllers.NetworkController
import com.mycorp.twitchapprxjava.api.controllers.NetworkControllerImpl
import org.koin.dsl.module

val networkModule = module {
    single<NetworkController> { NetworkControllerImpl(get()) }
}