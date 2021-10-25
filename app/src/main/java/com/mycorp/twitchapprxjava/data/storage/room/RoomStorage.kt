package com.mycorp.twitchapprxjava.data.storage.room

import com.mycorp.twitchapprxjava.data.storage.Storage
import com.mycorp.twitchapprxjava.data.storage.model.FollowerInfo
import com.mycorp.twitchapprxjava.data.storage.model.GameData
import com.mycorp.twitchapprxjava.data.storage.room.entities.FollowerInfoEntity
import com.mycorp.twitchapprxjava.data.storage.room.entities.GameDataEntity
import io.reactivex.Single

class RoomStorage(private val gameDataDao: GameDataDao, private val followersDao: FollowersDao) :
    Storage {

    override fun getGamesDataFromDb(): Single<List<GameDataEntity>> {
        return gameDataDao.getAllGames()
    }

    override fun insertGamesData(gamesData: List<GameData>) =
        gameDataDao.insertAll(gamesData.map { GameDataEntity.fromGameData(it) })

    override fun insertFollowersData(followersData: List<FollowerInfo>) = followersDao.insertAll(followersData.map { FollowerInfoEntity.fromFollowerInfo(it) })

}