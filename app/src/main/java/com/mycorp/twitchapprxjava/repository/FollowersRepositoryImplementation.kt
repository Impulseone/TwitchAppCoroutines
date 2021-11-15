package com.mycorp.twitchapprxjava.repository

import com.mycorp.twitchapprxjava.api.controllers.NetworkController
import com.mycorp.twitchapprxjava.database.Storage
import com.mycorp.twitchapprxjava.database.model.FollowerInfo
import io.reactivex.Completable
import io.reactivex.Single

class FollowersRepositoryImplementation(
    private val networkController: NetworkController,
    private val storage: Storage
) : FollowersRepository {
    override fun getFollowersListFromServer(id: String): Single<List<FollowerInfo>> =
        networkController.getGameItemDataFromNetwork(id).map {
            it.follows?.map { FollowerInfo.fromFollowerDto(it!!) }
        }

    override fun getFollowersListFromDbByIds(followerIds: List<String>) =
        storage.getFollowersFromDbByIds(followerIds)
            .map { it.map { FollowerInfo.fromFollowerInfoEntity(it) } }

    override fun getFollowersIdFromDbByGameId(gameId: String): Single<List<String>> {
        return storage.getFollowersIdFromDbByGameId(gameId)
    }

    override fun insertFollowersToDb(
        followersList: List<FollowerInfo>,
        gameId: String
    ): Completable {
       return storage.insertFollowersData(followersList, gameId)
    }
}