package com.mycorp.twitchapprxjava.domain.repository

import com.mycorp.twitchapprxjava.data.storage.model.FollowerInfo
import com.mycorp.twitchapprxjava.data.storage.model.GameData
import com.mycorp.twitchapprxjava.data.storage.model.GameItemDataDto
import io.reactivex.Completable
import io.reactivex.Single

interface Repository {
    fun getGamesDataFromNetwork(): Single<List<GameData>>
    fun getFollowersList(id: String): Single<List<FollowerInfo>>
    fun getGamesDataFromDb(): Single<List<GameData>>
    fun insertGamesDataToDb(gameDataTables: List<GameData>): Completable
}