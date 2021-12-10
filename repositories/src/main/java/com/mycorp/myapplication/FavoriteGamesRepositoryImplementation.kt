package com.mycorp.myapplication

import com.mycorp.database.storage.FavoriteGamesStorage
import com.mycorp.model.GameData

class FavoriteGamesRepositoryImplementation(
    private val favoriteGamesStorage: FavoriteGamesStorage
) : FavoriteGamesRepository {

    override fun getFavoriteGames() = favoriteGamesStorage.getFavoriteGamesList()

    override suspend fun checkIsFavorite(gameId: String) =
        favoriteGamesStorage.checkIsFavorite(gameId)

    override suspend fun insertFavoriteGame(gameData: GameData) =
        favoriteGamesStorage.insertFavoriteGame(gameData)

    override suspend fun deleteByGameId(gameId: String) =
        favoriteGamesStorage.deleteFavoriteByGameId(gameId)
}