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

    override suspend fun getGameDataEntityByIdSuspend(id: String) =
        gameDataDao.getGameByIdSuspend(id)

    override suspend fun insertGamesDataSuspend(gamesData: List<GameData>) =
        gameDataDao.insertAllSuspend(gamesData.map { GameDataEntity(it) })
}