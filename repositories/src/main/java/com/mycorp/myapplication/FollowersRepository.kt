package com.mycorp.myapplication

import com.mycorp.model.FollowerInfo

interface FollowersRepository {
    suspend fun fetchFollowers(id: String): List<FollowerInfo>
    suspend fun getFollowersByGameId(gameId:String): List<FollowerInfo>
}