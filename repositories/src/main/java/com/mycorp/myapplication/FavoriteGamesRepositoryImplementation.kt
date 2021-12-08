package com.mycorp.myapplication

import com.mycorp.database.storage.FavoriteGamesStorage
import com.mycorp.model.GameData

class FavoriteGamesRepositoryImplementation(
    private val favoriteGamesStorage: FavoriteGamesStorage
) : FavoriteGamesRepository {

    override fun getFavoriteGamesList() = favoriteGamesStorage.getFavoriteGamesList()

    override suspend fun checkIsFavoriteSuspend(gameId: String) = favoriteGamesStorage.checkIsFavoriteSuspend(gameId)

    override suspend fun insertFavoriteGame(gameData: GameData) {
        favoriteGamesStorage.insertFavoriteGame(gameData)
    }

    override suspend fun deleteByGameId(gameId: String) {
        favoriteGamesStorage.deleteFavoriteByGameId(gameId)
    }

}