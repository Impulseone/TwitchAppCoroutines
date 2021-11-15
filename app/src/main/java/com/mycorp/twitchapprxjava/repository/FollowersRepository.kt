package com.mycorp.twitchapprxjava.repository

import com.mycorp.twitchapprxjava.database.model.FollowerInfo
import io.reactivex.Completable
import io.reactivex.Single

interface FollowersRepository {
    fun getFollowersListFromServer(id: String): Single<List<FollowerInfo>>
    fun getFollowersListFromDbByIds(followerIds: List<String>): Single<List<FollowerInfo>>
    fun getFollowersIdFromDbByGameId(gameId: String): Single<List<String>>
    fun insertFollowersToDb(followersList: List<FollowerInfo>, gameId: String): Completable
}