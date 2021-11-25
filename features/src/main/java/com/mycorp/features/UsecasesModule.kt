package com.mycorp.features

import com.mycorp.features.usecases.FavoriteGamesUseCase
import com.mycorp.features.usecases.FavoriteGamesUseCaseImpl
import com.mycorp.features.usecases.GameDataUseCase
import com.mycorp.features.usecases.GameDataUseCaseImpl
import org.koin.dsl.module

val usecasesModule = module {
    single<GameDataUseCase> { GameDataUseCaseImpl(get(), get(), get()) }
    single<FavoriteGamesUseCase> { FavoriteGamesUseCaseImpl(get()) }
}