package com.mycorp.myapplication

import com.mycorp.model.FollowerInfo
import io.reactivex.Single

interface FollowersRepository {
    fun fetchFollowers(id: String): Single<List<FollowerInfo>>
    suspend fun fetchFollowersSuspend(id: String): List<FollowerInfo>

    fun getFollowersByGameId(gameId:String): Single<List<FollowerInfo>>
    suspend fun getFollowersByGameIdSuspend(gameId:String): List<FollowerInfo>
}