package com.mycorp.database.storage

import com.mycorp.database.dao.GameDataDao
import com.mycorp.database.entities.GameDataEntity
import com.mycorp.model.GameData

class GamesStorageImplementation(
    private val gameDataDao: GameDataDao,
) :
    GamesStorage {

    override fun getGamesDataPaging() =
        gameDataDao.getAllGamesPaging()

    override suspend fun deleteAllGames() = gameDataDao.deleteAll()

    override suspend fun getGameDataEntityById(id: String) =
        gameDataDao.getGameByIdSuspend(id)

    override suspend fun insertGamesData(gamesData: List<GameData>) =
        gameDataDao.insertAll(gamesData.map { GameDataEntity(it) })
}