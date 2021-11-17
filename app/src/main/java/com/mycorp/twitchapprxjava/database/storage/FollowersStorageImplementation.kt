package com.mycorp.twitchapprxjava.database.storage

import com.mycorp.twitchapprxjava.database.dao.FollowersDao
import com.mycorp.twitchapprxjava.database.dao.GameFollowersDao
import com.mycorp.twitchapprxjava.database.entities.FollowerInfoEntity
import com.mycorp.twitchapprxjava.database.entities.GameFollowersEntity
import com.mycorp.twitchapprxjava.models.FollowerInfo
import io.reactivex.Completable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class FollowersStorageImplementation(
    private val followersDao: FollowersDao,
    private val gameFollowersDao: GameFollowersDao
) : FollowersStorage {
    override fun getFollowersByIds(followerIds: List<String>) =
        followersDao.getByIds(followerIds)

    override fun getFollowersIdByGameId(gameId: String) =
        gameFollowersDao.getGameFollowersById(gameId).map { it.followersId }

    override fun insertFollowersData(
        followersData: List<FollowerInfo>,
        gameId: String
    ): Completable {
        gameFollowersDao.insert(GameFollowersEntity(followersData, gameId))
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe()
        return followersDao.insertAll(followersData.map { FollowerInfoEntity.fromFollowerInfo(it) })
    }
}