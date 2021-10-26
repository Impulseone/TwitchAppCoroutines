package com.mycorp.twitchapprxjava.data.storage

import com.mycorp.twitchapprxjava.data.storage.model.FollowerInfo
import com.mycorp.twitchapprxjava.data.storage.model.GameData
import com.mycorp.twitchapprxjava.data.storage.model.GameItemData
import com.mycorp.twitchapprxjava.data.storage.room.entities.FollowerInfoEntity
import com.mycorp.twitchapprxjava.data.storage.room.entities.GameDataEntity
import com.mycorp.twitchapprxjava.data.storage.room.entities.GameItemDataEntity
import io.reactivex.Completable
import io.reactivex.Single

interface Storage {

    fun getGamesDataFromDb(): Single<List<GameDataEntity>>
    fun getFollowersFromDb(): Single<List<FollowerInfoEntity>>
    fun getGameItemData(gameId:String): Single<GameItemDataEntity>

    fun insertGamesData(gamesData: List<GameData>): Completable
    fun insertFollowersData(followersData: List<FollowerInfo>): Completable
    fun insertGameItemData(gameItemData: GameItemData): Completable
}