package com.mycorp.twitchapprxjava.data.storage.room

import com.mycorp.twitchapprxjava.data.storage.Storage
import com.mycorp.twitchapprxjava.data.storage.model.GameData
import com.mycorp.twitchapprxjava.data.storage.model.GameDataEntity
import io.reactivex.Single

class RoomStorage(private val gameDataDao: GameDataDao) : Storage {

    override fun getGamesDataFromDb(): Single<List<GameDataEntity>> {
        return gameDataDao.getAllGames()
    }

    override fun insertGamesData(gamesData: List<GameData>) =
        gameDataDao.insertAll(gamesData.map { it.toGameDataEntity() })
}