package com.mycorp.twitchapprxjava.repository

import com.mycorp.model.FollowerInfo
import io.reactivex.Single

interface FollowersRepository {
    fun fetchFollowers(id: String): Single<List<FollowerInfo>>

    fun getFollowersByGameId(gameId:String): Single<List<FollowerInfo>>
}