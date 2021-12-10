package com.mycorp.myapplication

import com.mycorp.model.FollowerInfo
import io.reactivex.Single

interface FollowersRepository {
    suspend fun fetchFollowers(id: String): List<FollowerInfo>
    suspend fun getFollowersByGameId(gameId:String): List<FollowerInfo>
}