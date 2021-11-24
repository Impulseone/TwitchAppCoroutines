package com.mycorp.twitchapprxjava.database.storage

import com.mycorp.model.GameData
import com.mycorp.twitchapprxjava.database.dao.FavoriteGameDataDao
import com.mycorp.twitchapprxjava.database.entities.FavoriteGameDataEntity

class FavoriteGamesStorageImplementation(private val favoriteGameDataDao: FavoriteGameDataDao) :
    FavoriteGamesStorage {
    override fun checkIsFavorite(gameId: String) = favoriteGameDataDao.checkExist(gameId)

    override fun insertFavoriteGame(gameData: GameData) = favoriteGameDataDao.insert(
        FavoriteGameDataEntity(gameData)
    )

    override fun deleteFavoriteByGameId(gameId: String) = favoriteGameDataDao.deleteByGameId(gameId)

    override fun getFavoriteGames(limit: Int, offset: Int) =
        favoriteGameDataDao.getAll(limit, offset)


}