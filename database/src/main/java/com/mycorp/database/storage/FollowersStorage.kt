package com.mycorp.database.storage

import com.mycorp.model.FollowerInfo
import io.reactivex.Completable
import io.reactivex.Single

interface FollowersStorage {
    fun getFollowersByGameId(gameId: String): Single<List<FollowerInfo>>
    suspend fun getFollowersByGameIdSuspend(gameId: String): List<FollowerInfo>
    fun insertFollowersData(followersData: List<FollowerInfo>, gameId: String): Completable
    suspend fun insertFollowersDataSuspend(followersData: List<FollowerInfo>, gameId: String)
}