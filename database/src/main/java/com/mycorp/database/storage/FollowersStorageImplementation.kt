package com.mycorp.database.storage

import com.mycorp.database.dao.FollowersDao
import com.mycorp.database.dao.GameFollowersDao
import com.mycorp.database.entities.FollowerInfoEntity
import com.mycorp.database.entities.GameFollowersEntity
import com.mycorp.model.FollowerInfo

class FollowersStorageImplementation(
    private val followersDao: FollowersDao,
    private val gameFollowersDao: GameFollowersDao
) : FollowersStorage {

    override suspend fun getFollowersByGameId(gameId: String): List<FollowerInfo> {
        val followersId = gameFollowersDao.getGameFollowersById(gameId).followersId
        return followersDao.getByIds(followersId).map {
            it.toModel()
        }
    }

    override suspend fun insertFollowersData(
        followersData: List<FollowerInfo>,
        gameId: String
    ) {
        gameFollowersDao.insert((GameFollowersEntity(followersData, gameId)))
        followersDao.insertAll(followersData.map { FollowerInfoEntity(it) })
    }
}