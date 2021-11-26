package com.mycorp.features.usecases

import com.mycorp.model.GameData
import io.reactivex.Completable

interface FavoriteGamesUseCase {
    fun saveGame(gameData: GameData): Completable
    fun deleteGameById(id: String): Completable
}