package com.mycorp.twitchapprxjava.repository

import com.mycorp.twitchapprxjava.api.controllers.FollowersController
import com.mycorp.twitchapprxjava.database.storage.FollowersStorage
import com.mycorp.twitchapprxjava.models.FollowerInfo
import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class FollowersRepositoryImplementation(
    private val followersController: FollowersController,
    private val followersStorage: FollowersStorage
) : FollowersRepository {
    override fun fetchFollowers(id: String): Single<List<FollowerInfo>> {
        return followersController.getGameItemDataFromNetwork(id)
            .map {
                it.follows ?: listOf()
            }
            .map { list ->
                list.filterNotNull().map { FollowerInfo.fromFollowerDto(it) }
            }
            .flatMap {
                followersStorage.insertFollowersData(it, id)
                    .andThen(Single.just(it))
            }
    }

    override fun getFollowersByIds(followerIds: List<String>) =
        followersStorage.getFollowersByIds(followerIds)
            .map { it.map { entity -> FollowerInfo.fromFollowerInfoEntity(entity) } }

    override fun getFollowersIdByGameId(gameId: String): Single<List<String>> {
        return followersStorage.getFollowersIdByGameId(gameId)
    }
}