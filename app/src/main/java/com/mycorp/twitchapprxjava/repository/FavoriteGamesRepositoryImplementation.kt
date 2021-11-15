package com.mycorp.twitchapprxjava.repository

import com.mycorp.twitchapprxjava.database.Storage
import com.mycorp.twitchapprxjava.database.model.FavoriteGameData
import com.mycorp.twitchapprxjava.database.model.GameData

class FavoriteGamesRepositoryImplementation(
    private val storage: Storage
) : FavoriteGamesRepository {
    override fun getFavoriteGames() = storage.getFavoriteGames()
        .map {
            FavoriteGameData(it)
        }

    override fun checkIsFavorite(gameId: String) = storage.checkIsFavorite(gameId)

    override fun insertFavoriteGame(gameData: GameData) = storage.insertFavoriteGame(gameData)

    override fun deleteByGameId(gameId: String) = storage.deleteFavoriteByGameId(gameId)

}