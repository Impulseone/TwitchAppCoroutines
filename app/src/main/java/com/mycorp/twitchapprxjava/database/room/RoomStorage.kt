package com.mycorp.twitchapprxjava.database.room

import com.mycorp.twitchapprxjava.database.Storage
import com.mycorp.twitchapprxjava.database.model.FollowerInfo
import com.mycorp.twitchapprxjava.database.model.GameData
import com.mycorp.twitchapprxjava.database.room.dao.FavoriteGameDataDao
import com.mycorp.twitchapprxjava.database.room.dao.FollowersDao
import com.mycorp.twitchapprxjava.database.room.dao.GameDataDao
import com.mycorp.twitchapprxjava.database.room.entities.FavoriteGameDataEntity
import com.mycorp.twitchapprxjava.database.room.entities.FollowerInfoEntity
import com.mycorp.twitchapprxjava.database.room.entities.GameDataEntity

class RoomStorage(
    private val gameDataDao: GameDataDao,
    private val followersDao: FollowersDao,
    private val favoriteGameDataDao: FavoriteGameDataDao
) :
    Storage {

    override fun getGamesDataFromDb() = gameDataDao.getAllGames()

    override fun getGameDataEntityById(id: String) = gameDataDao.getGameById(id)

    override fun getFollowersFromDbByIds(followerIds: List<String>) =
        followersDao.getByIds(followerIds)

    override fun getFavoriteGamesFromDb() = favoriteGameDataDao.getAll()

    override fun insertGamesData(gamesData: List<GameData>) =
        gameDataDao.insertAll(gamesData.map { GameDataEntity.fromGameData(it) })

    override fun insertFollowersData(followersData: List<FollowerInfo>) =
        followersDao.insertAll(followersData.map { FollowerInfoEntity.fromFollowerInfo(it) })

    override fun checkIsFavorite(gameId: String) = favoriteGameDataDao.checkExist(gameId)

    override fun insertFavoriteGame(gameData: GameData) = favoriteGameDataDao.insert(
        FavoriteGameDataEntity(gameData)
    )

    override fun deleteByGameId(gameId: String) = favoriteGameDataDao.deleteByGameId(gameId)

}