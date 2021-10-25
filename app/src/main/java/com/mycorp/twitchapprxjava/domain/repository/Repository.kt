package com.mycorp.twitchapprxjava.domain.repository

import com.mycorp.twitchapprxjava.data.storage.model.FollowerInfo
import com.mycorp.twitchapprxjava.data.storage.model.GameData
import com.mycorp.twitchapprxjava.data.storage.model.GameItemDataDto
import io.reactivex.Completable
import io.reactivex.Single

interface Repository {

    fun getGamesDataFromServer(): Single<List<GameData>>
    fun getFollowersListFromServer(id: String): Single<List<FollowerInfo>>

    fun getGamesDataFromDb(): Single<List<GameData>>
    fun getFollowersListFromDb(id: String): Single<List<FollowerInfo>>

    fun insertGamesDataToDb(gameDataEntities: List<GameData>): Completable
    fun insertFollowersToDb(followersList: List<FollowerInfo>): Completable

}