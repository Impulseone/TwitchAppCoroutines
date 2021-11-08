package com.mycorp.twitchapprxjava.repository

import androidx.paging.DataSource
import com.mycorp.twitchapprxjava.database.model.FollowerInfo
import com.mycorp.twitchapprxjava.database.model.GameData
import com.mycorp.twitchapprxjava.database.model.SingleGameData
import io.reactivex.Completable
import io.reactivex.Single

interface Repository {

    fun getGamesDataFromServer(): Single<List<GameData>>
    fun getFollowersListFromServer(id: String): Single<List<FollowerInfo>>

    fun getGamesDataFromDb(): Single<List<GameData>>
    fun getGameDataById(id: String): Single<GameData>
    fun getFollowersListFromDbByIds(followerIds: List<String>): Single<List<FollowerInfo>>
    fun getSingleGameDataFromDb(gameId: String): Single<SingleGameData>
    fun getFavoriteGamesFromDb(): DataSource.Factory<Int, SingleGameData>

    fun insertGamesDataToDb(gameDataEntities: List<GameData>): Completable
    fun insertFollowersToDb(followersList: List<FollowerInfo>): Completable
    fun saveSingleGameDataToDb(singleGameData: SingleGameData): Completable

}