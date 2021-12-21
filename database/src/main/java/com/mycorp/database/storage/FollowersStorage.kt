package com.mycorp.database.storage

import com.mycorp.model.FollowerInfo

interface FollowersStorage {
    suspend fun getFollowersByGameId(gameId: String): List<FollowerInfo>
    suspend fun insertFollowersData(followersData: List<FollowerInfo>, gameId: String)
}