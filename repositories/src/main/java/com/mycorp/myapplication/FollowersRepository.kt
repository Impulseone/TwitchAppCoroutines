package com.mycorp.myapplication

import com.mycorp.model.FollowerInfo
import io.reactivex.Single

interface FollowersRepository {
    suspend fun fetchFollowersSuspend(id: String): List<FollowerInfo>
    suspend fun getFollowersByGameIdSuspend(gameId:String): List<FollowerInfo>
}