package com.mycorp.games

import com.mycorp.favorite_games.FavoriteGamesUseCase
import com.mycorp.favorite_games.FavoriteGamesUseCaseImpl
import com.mycorp.games.usecases.GameDataUseCase
import com.mycorp.games.usecases.GameDataUseCaseImpl
import org.koin.dsl.module

val usecasesModule = module {
    single<GameDataUseCase> { GameDataUseCaseImpl(get(), get(), get()) }
    single<FavoriteGamesUseCase> { FavoriteGamesUseCaseImpl(get()) }
}