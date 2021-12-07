package com.mycorp.database.storage

import com.mycorp.database.dao.GameDataDao
import com.mycorp.database.entities.GameDataEntity
import com.mycorp.model.GameData
import io.reactivex.Single

class GamesStorageImplementation(
    private val gameDataDao: GameDataDao,
) :
    GamesStorage {

    override fun getGamesData() = gameDataDao.getAllGames()

    override fun getGamesLimited(limit: Int, offset: Int) =
        gameDataDao.getGamesLimited(limit, offset)

    override fun getGameDataEntityById(id: String) = gameDataDao.getGameById(id)

    override suspend fun getGameDataEntityByIdSuspend(id: String): GameDataEntity {
        return gameDataDao.getGameByIdSuspend(id)
    }

    override fun insertGamesData(gamesData: List<GameData>) =
        gameDataDao.insertAll(gamesData.map { GameDataEntity(it) })
}