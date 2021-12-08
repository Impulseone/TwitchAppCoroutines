package com.mycorp.database.storage

import com.mycorp.database.dao.FavoriteGameDataDao
import com.mycorp.database.entities.FavoriteGameDataEntity
import com.mycorp.model.GameData

class FavoriteGamesStorageImplementation(private val favoriteGameDataDao: FavoriteGameDataDao) :
    FavoriteGamesStorage {

    override suspend fun checkIsFavorite(gameId: String) =
        favoriteGameDataDao.checkExistSuspend(gameId) > 0

    override suspend fun insertFavoriteGame(gameData: GameData) {
        favoriteGameDataDao.insert(
            FavoriteGameDataEntity(gameData)
        )
    }

    override suspend fun deleteFavoriteByGameId(gameId: String) {
        favoriteGameDataDao.deleteByGameId(gameId)
    }

    override fun getFavoriteGamesList() = favoriteGameDataDao.getAll()
}