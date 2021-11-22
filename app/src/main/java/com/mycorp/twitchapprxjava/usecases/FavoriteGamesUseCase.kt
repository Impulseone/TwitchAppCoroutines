package com.mycorp.twitchapprxjava.usecases

import com.mycorp.twitchapprxjava.models.GameData
import io.reactivex.Completable

interface FavoriteGamesUseCase {
    fun saveGame(gameData: GameData): Completable
    fun deleteGameById(id: String): Completable
}