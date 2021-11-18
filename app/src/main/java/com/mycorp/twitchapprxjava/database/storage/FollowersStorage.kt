package com.mycorp.twitchapprxjava.database.storage

import com.mycorp.twitchapprxjava.database.entities.FollowerInfoEntity
import com.mycorp.twitchapprxjava.models.FollowerInfo
import io.reactivex.Completable
import io.reactivex.Single

interface FollowersStorage {
    fun getFollowersByIds(followerIds: List<String>): Single<List<FollowerInfoEntity>>
    fun getFollowersIdByGameId(gameId: String): Single<List<String>>
    fun insertFollowersData(followersData: List<FollowerInfo>, gameId: String): Completable
}