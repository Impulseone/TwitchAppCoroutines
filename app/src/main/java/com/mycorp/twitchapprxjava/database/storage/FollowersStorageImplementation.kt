package com.mycorp.twitchapprxjava.database.storage

import com.mycorp.twitchapprxjava.database.dao.FollowersDao
import com.mycorp.twitchapprxjava.database.dao.GameFollowersDao
import com.mycorp.twitchapprxjava.database.entities.FollowerInfoEntity
import com.mycorp.twitchapprxjava.database.entities.GameFollowersEntity
import com.mycorp.twitchapprxjava.models.FollowerInfo
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
                    FollowerInfo(it)
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
        return followersDao.insertAll(followersData.map { FollowerInfoEntity.fromFollowerInfo(it) })
    }
}