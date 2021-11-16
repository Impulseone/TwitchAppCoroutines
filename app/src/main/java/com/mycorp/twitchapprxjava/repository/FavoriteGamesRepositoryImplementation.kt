package com.mycorp.twitchapprxjava.repository

import com.mycorp.twitchapprxjava.database.Storage
import com.mycorp.twitchapprxjava.models.FavoriteGameData
import com.mycorp.twitchapprxjava.models.GameData
import com.mycorp.twitchapprxjava.models.ListItemData

class FavoriteGamesRepositoryImplementation(
    private val storage: Storage
) : FavoriteGamesRepository {
    override fun getFavoriteGames(limit: Int, offset: Int) = storage.getFavoriteGames(limit, offset)
        .map {
            it.map {
                ListItemData(it.id, FavoriteGameData(it))
            }
        }

    override fun checkIsFavorite(gameId: String) = storage.checkIsFavorite(gameId)

    override fun insertFavoriteGame(gameData: GameData) = storage.insertFavoriteGame(gameData)

    override fun deleteByGameId(gameId: String) = storage.deleteFavoriteByGameId(gameId)

}