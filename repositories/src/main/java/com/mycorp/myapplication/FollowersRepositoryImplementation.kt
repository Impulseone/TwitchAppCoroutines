package com.mycorp.myapplication

import com.mycorp.api.controllers.FollowersController
import com.mycorp.database.storage.FollowersStorage
import com.mycorp.model.FollowerInfo
import io.reactivex.Single

class FollowersRepositoryImplementation(
    private val followersController: FollowersController,
    private val followersStorage: FollowersStorage
) : FollowersRepository {

    override suspend fun fetchFollowersSuspend(id: String): List<FollowerInfo> {
        val followers = followersController.getGameItemDataFromNetworkSuspend(id).follows?.map {
            it!!.toModel()
        }
        followers?.let { followersStorage.insertFollowersDataSuspend(it, id) }
        return followers ?: listOf()
    }

    override suspend fun getFollowersByGameIdSuspend(gameId: String): List<FollowerInfo> {
        return followersStorage.getFollowersByGameIdSuspend(gameId)
    }
}