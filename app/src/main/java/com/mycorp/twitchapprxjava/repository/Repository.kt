package com.mycorp.twitchapprxjava.repository

import androidx.paging.DataSource
import com.mycorp.twitchapprxjava.database.model.FollowerInfo
import com.mycorp.twitchapprxjava.database.model.GameData
import com.mycorp.twitchapprxjava.database.model.SingleGameData
import io.reactivex.Completable
import io.reactivex.Single

interface Repository {

    //TODO: Remove from here to GamesRepository
    fun getGameDataById(id: String): Single<GameData>

    fun getFavoriteGamesFromDb(): DataSource.Factory<Int, SingleGameData>

    fun getSingleGameDataFromDb(gameId: String): Single<SingleGameData>
    fun saveSingleGameDataToDb(singleGameData: SingleGameData): Completable

    //TODO: Remove from here to FollowersRepository
    fun getFollowersListFromDbByIds(followerIds: List<String>): Single<List<FollowerInfo>>
    fun getFollowersListFromServer(id: String): Single<List<FollowerInfo>>
    fun insertFollowersToDb(followersList: List<FollowerInfo>): Completable
}