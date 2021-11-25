package com.mycorp.myapplication

import com.mycorp.database.storage.FavoriteGamesStorage
import com.mycorp.model.GameData
import com.mycorp.model.ListItemData

class FavoriteGamesRepositoryImplementation(
    private val favoriteGamesStorage: FavoriteGamesStorage
) : FavoriteGamesRepository {
    override fun getFavoriteGames(limit: Int, offset: Int) = favoriteGamesStorage.getFavoriteGames(limit, offset)
        .map {
            it.map { entity ->
                ListItemData(entity.id, entity.toModel())
            }
        }

    override fun checkIsFavorite(gameId: String) = favoriteGamesStorage.checkIsFavorite(gameId)

    override fun insertFavoriteGame(gameData: GameData) = favoriteGamesStorage.insertFavoriteGame(gameData)

    override fun deleteByGameId(gameId: String) = favoriteGamesStorage.deleteFavoriteByGameId(gameId)

}