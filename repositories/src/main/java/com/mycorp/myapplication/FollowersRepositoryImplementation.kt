package com.mycorp.myapplication

import com.mycorp.api.controllers.FollowersController
import com.mycorp.database.storage.FollowersStorage
import com.mycorp.model.FollowerInfo

class FollowersRepositoryImplementation(
    private val followersController: FollowersController,
    private val followersStorage: FollowersStorage
) : FollowersRepository {

    override suspend fun fetchFollowers(id: String): List<FollowerInfo> {
        val followers = followersController.getGameItemDataFromNetwork(id).follows?.map {
            it!!.toModel()
        }
        followers?.let { followersStorage.insertFollowersDataSuspend(it, id) }
        return followers ?: listOf()
    }

    override suspend fun getFollowersByGameId(gameId: String): List<FollowerInfo> {
        return followersStorage.getFollowersByGameIdSuspend(gameId)
    }
}