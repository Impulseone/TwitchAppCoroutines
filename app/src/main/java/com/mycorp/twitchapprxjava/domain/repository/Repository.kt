package com.mycorp.twitchapprxjava.domain.repository

import com.mycorp.twitchapprxjava.data.storage.model.GameData
import io.reactivex.Completable
import io.reactivex.Single

interface Repository {
    fun getGamesDataFromNetwork(): Single<List<GameData>>
    fun getGamesDataFromDb(): Single<List<GameData>>
    fun insertGamesDataToDb(gameDataTables: List<GameData>): Completable
}