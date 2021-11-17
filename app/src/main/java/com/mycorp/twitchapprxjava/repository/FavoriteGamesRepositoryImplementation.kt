package com.mycorp.twitchapprxjava.repository

import com.mycorp.twitchapprxjava.database.storage.FavoriteGamesStorage
import com.mycorp.twitchapprxjava.models.FavoriteGameData
import com.mycorp.twitchapprxjava.models.GameData
import com.mycorp.twitchapprxjava.models.ListItemData

class FavoriteGamesRepositoryImplementation(
    private val favoriteGamesStorage: FavoriteGamesStorage
) : FavoriteGamesRepository {
    override fun getFavoriteGames(limit: Int, offset: Int) = favoriteGamesStorage.getFavoriteGames(limit, offset)
        .map {
            it.map {
                ListItemData(it.id, FavoriteGameData(it))
            }
        }

    override fun checkIsFavorite(gameId: String) = favoriteGamesStorage.checkIsFavorite(gameId)

    override fun insertFavoriteGame(gameData: GameData) = favoriteGamesStorage.insertFavoriteGame(gameData)

    override fun deleteByGameId(gameId: String) = favoriteGamesStorage.deleteFavoriteByGameId(gameId)

}