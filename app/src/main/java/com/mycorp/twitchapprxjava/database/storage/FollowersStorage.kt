package com.mycorp.twitchapprxjava.database.storage

import com.mycorp.twitchapprxjava.models.FollowerInfo
import io.reactivex.Completable
import io.reactivex.Single

interface FollowersStorage {
    fun getFollowersByGameId(gameId: String): Single<List<FollowerInfo>>
    fun insertFollowersData(followersData: List<FollowerInfo>, gameId: String): Completable
}