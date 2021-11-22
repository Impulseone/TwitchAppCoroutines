package com.mycorp.twitchapprxjava.di

import com.mycorp.twitchapprxjava.usecases.FavoriteGamesUseCase
import com.mycorp.twitchapprxjava.usecases.FavoriteGamesUseCaseImpl
import com.mycorp.twitchapprxjava.usecases.GameDataUseCase
import com.mycorp.twitchapprxjava.usecases.GameDataUseCaseImpl
import org.koin.dsl.module

val usecasesModule = module {
    single<GameDataUseCase> { GameDataUseCaseImpl(get(), get(), get()) }
    single<FavoriteGamesUseCase> { FavoriteGamesUseCaseImpl(get()) }
}