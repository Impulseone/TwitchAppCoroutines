package com.mycorp.database.storage

import androidx.paging.PagingSource
import com.mycorp.database.dao.FavoriteGameDataDao
import com.mycorp.database.entities.FavoriteGameDataEntity
import com.mycorp.model.GameData

class FavoriteGamesStorageImplementation(private val favoriteGameDataDao: FavoriteGameDataDao) :
    FavoriteGamesStorage {
    override fun checkIsFavorite(gameId: String) = favoriteGameDataDao.checkExist(gameId)

    override fun insertFavoriteGame(gameData: GameData) = favoriteGameDataDao.insert(
        FavoriteGameDataEntity(gameData)
    )

    override fun deleteFavoriteByGameId(gameId: String) = favoriteGameDataDao.deleteByGameId(gameId)

    override fun getFavoriteGames(limit: Int, offset: Int) =
        favoriteGameDataDao.getAll(limit, offset)

    override fun getFavoriteGamesList(): PagingSource<Int, FavoriteGameDataEntity> {
        return favoriteGameDataDao.getAllList()
    }
}