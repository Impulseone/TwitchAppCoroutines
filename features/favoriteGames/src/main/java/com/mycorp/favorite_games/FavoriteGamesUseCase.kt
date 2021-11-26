package com.mycorp.favorite_games

import com.mycorp.model.GameData
import io.reactivex.Completable

interface FavoriteGamesUseCase {
    fun saveGame(gameData: GameData): Completable
    fun deleteGameById(id: String): Completable
}