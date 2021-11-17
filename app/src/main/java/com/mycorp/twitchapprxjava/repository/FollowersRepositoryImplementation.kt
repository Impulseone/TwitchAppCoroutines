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
    override fun fetchFollowers(id: String): Single<List<FollowerInfo>> =
        followersController.getGameItemDataFromNetwork(id).map {
            val followers = it.follows?.map { followerDto -> FollowerInfo.fromFollowerDto(followerDto!!) }
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
        followersStorage.getFollowersByIds(followerIds)
            .map { it.map { entity -> FollowerInfo.fromFollowerInfoEntity(entity) } }

    override fun getFollowersIdByGameId(gameId: String): Single<List<String>> {
        return followersStorage.getFollowersIdByGameId(gameId)
    }

    private fun insertFollowers(
        followersList: List<FollowerInfo>,
        gameId: String
    ): Completable {
        return followersStorage.insertFollowersData(followersList, gameId)
    }
}