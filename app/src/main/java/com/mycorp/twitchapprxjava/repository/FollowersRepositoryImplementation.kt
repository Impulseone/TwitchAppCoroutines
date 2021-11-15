package com.mycorp.twitchapprxjava.repository

import com.mycorp.twitchapprxjava.api.controllers.NetworkController
import com.mycorp.twitchapprxjava.database.Storage
import com.mycorp.twitchapprxjava.database.model.FollowerInfo
import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class FollowersRepositoryImplementation(
    private val networkController: NetworkController,
    private val storage: Storage
) : FollowersRepository {
    override fun getFollowersListFromServer(id: String): Single<List<FollowerInfo>> =
        networkController.getGameItemDataFromNetwork(id).map {
            val followers = it.follows?.map { FollowerInfo.fromFollowerDto(it!!) }
            if (followers != null) {
                insertFollowersToDb(followers, id)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({},{})
                    .dispose()
            }
            return@map followers
        }

    override fun getFollowersListFromDbByIds(followerIds: List<String>) =
        storage.getFollowersFromDbByIds(followerIds)
            .map { it.map { FollowerInfo.fromFollowerInfoEntity(it) } }

    override fun getFollowersIdFromDbByGameId(gameId: String): Single<List<String>> {
        return storage.getFollowersIdFromDbByGameId(gameId)
    }

    private fun insertFollowersToDb(
        followersList: List<FollowerInfo>,
        gameId: String
    ): Completable {
        return storage.insertFollowersData(followersList, gameId)
    }
}