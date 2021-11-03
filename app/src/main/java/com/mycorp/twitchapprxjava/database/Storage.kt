package com.mycorp.twitchapprxjava.database

import androidx.paging.DataSource
import com.mycorp.twitchapprxjava.database.model.FollowerInfo
import com.mycorp.twitchapprxjava.database.model.GameData
import com.mycorp.twitchapprxjava.database.model.SingleGameData
import com.mycorp.twitchapprxjava.database.room.entities.FollowerInfoEntity
import com.mycorp.twitchapprxjava.database.room.entities.GameDataEntity
import com.mycorp.twitchapprxjava.database.room.entities.SingleGameDataEntity
import io.reactivex.Completable
import io.reactivex.Single

interface Storage {

    fun getGamesDataFromDb(): Single<List<GameDataEntity>>
    fun getFollowersFromDbByIds(followerIds: List<String>): Single<List<FollowerInfoEntity>>
    fun getGameItemData(gameId: String): Single<SingleGameDataEntity>
    fun getFavoriteGamesFromDb():  DataSource.Factory<Int, SingleGameDataEntity>

    fun insertGamesData(gamesData: List<GameData>): Completable
    fun insertFollowersData(followersData: List<FollowerInfo>): Completable
    fun saveSingleGameData(singleGameData: SingleGameData): Completable
}