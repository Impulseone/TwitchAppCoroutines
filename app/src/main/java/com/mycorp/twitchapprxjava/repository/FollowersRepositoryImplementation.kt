package com.mycorp.twitchapprxjava.repository

import com.mycorp.twitchapprxjava.api.controllers.NetworkController
import com.mycorp.twitchapprxjava.database.Storage
import com.mycorp.twitchapprxjava.models.FollowerInfo
import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class FollowersRepositoryImplementation(
    private val networkController: NetworkController,
    private val storage: Storage
) : FollowersRepository {
    override fun fetchFollowers(id: String): Single<List<FollowerInfo>> =
        networkController.getGameItemDataFromNetwork(id).map {
            val followers = it.follows?.map { FollowerInfo.fromFollowerDto(it!!) }
            if (followers != null) {
                insertFollowers(followers, id)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({},{})
                    .dispose()
            }
            return@map followers
        }

    override fun getFollowersByIds(followerIds: List<String>) =
        storage.getFollowersByIds(followerIds)
            .map { it.map { FollowerInfo.fromFollowerInfoEntity(it) } }

    override fun getFollowersIdByGameId(gameId: String): Single<List<String>> {
        return storage.getFollowersIdByGameId(gameId)
    }

    private fun insertFollowers(
        followersList: List<FollowerInfo>,
        gameId: String
    ): Completable {
        return storage.insertFollowersData(followersList, gameId)
    }
}