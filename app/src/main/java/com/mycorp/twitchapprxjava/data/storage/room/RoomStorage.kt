package com.mycorp.twitchapprxjava.data.storage.room

import com.mycorp.twitchapprxjava.data.storage.Storage
import com.mycorp.twitchapprxjava.data.storage.model.FollowerInfo
import com.mycorp.twitchapprxjava.data.storage.model.GameData
import com.mycorp.twitchapprxjava.data.storage.model.SingleGameData
import com.mycorp.twitchapprxjava.data.storage.room.dao.FollowersDao
import com.mycorp.twitchapprxjava.data.storage.room.dao.GameDataDao
import com.mycorp.twitchapprxjava.data.storage.room.dao.GameItemDataDao
import com.mycorp.twitchapprxjava.data.storage.room.entities.FollowerInfoEntity
import com.mycorp.twitchapprxjava.data.storage.room.entities.GameDataEntity
import com.mycorp.twitchapprxjava.data.storage.room.entities.GameItemDataEntity

class RoomStorage(
    private val gameDataDao: GameDataDao,
    private val followersDao: FollowersDao,
    private val gameItemDataDao: GameItemDataDao
) :
    Storage {

    override fun getGamesDataFromDb() = gameDataDao.getAllGames()

    override fun getFollowersFromDbByIds(followerIds: List<String>) = followersDao.getByIds(followerIds)

    override fun getGameItemData(gameId: String) = gameItemDataDao.getById(gameId)

    override fun insertGamesData(gamesData: List<GameData>) =
        gameDataDao.insertAll(gamesData.map { GameDataEntity.fromGameData(it) })

    override fun insertFollowersData(followersData: List<FollowerInfo>) =
        followersDao.insertAll(followersData.map { FollowerInfoEntity.fromFollowerInfo(it) })

    override fun insertGameItemData(singleGameData: SingleGameData) =
        gameItemDataDao.insert(GameItemDataEntity.fromGameItemData(singleGameData))

}