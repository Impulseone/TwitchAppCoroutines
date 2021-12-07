package com.mycorp.database.storage

import com.mycorp.database.dao.FollowersDao
import com.mycorp.database.dao.GameFollowersDao
import com.mycorp.database.entities.FollowerInfoEntity
import com.mycorp.database.entities.GameFollowersEntity
import com.mycorp.model.FollowerInfo
import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class FollowersStorageImplementation(
    private val followersDao: FollowersDao,
    private val gameFollowersDao: GameFollowersDao
) : FollowersStorage {

   override fun getFollowersByGameId(gameId: String): Single<List<FollowerInfo>> {
        return Single.just(gameId).flatMap {
            gameFollowersDao.getGameFollowersById(it).map { entity ->
                entity.followersId
            }
        }.flatMap { followersId ->
            followersDao.getByIds(followersId).map { followerEntities ->
                followerEntities.map {
                    it.toModel()
                }
            }
        }
    }

    override fun insertFollowersData(
        followersData: List<FollowerInfo>,
        gameId: String
    ): Completable {
        gameFollowersDao.insert(GameFollowersEntity(followersData, gameId))
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({}, {}).dispose()
        return followersDao.insertAll(followersData.map { FollowerInfoEntity(it) })
    }

    override suspend fun insertFollowersDataSuspend(
        followersData: List<FollowerInfo>,
        gameId: String
    ) {
        gameFollowersDao.insertSuspend((GameFollowersEntity(followersData, gameId)))
        followersDao.insertAllSuspend(followersData.map { FollowerInfoEntity(it)})
    }
}