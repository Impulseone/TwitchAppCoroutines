package com.mycorp.twitchapprxjava.database.storage

import com.mycorp.model.GameData
import com.mycorp.twitchapprxjava.database.dao.GameDataDao
import com.mycorp.twitchapprxjava.database.entities.GameDataEntity

class GamesStorageImplementation(
    private val gameDataDao: GameDataDao,
) :
    GamesStorage {

    override fun getGamesData() = gameDataDao.getAllGames()

    override fun getGameDataEntityById(id: String) = gameDataDao.getGameById(id)

    override fun insertGamesData(gamesData: List<GameData>) =
        gameDataDao.insertAll(gamesData.map { GameDataEntity(it) })
}