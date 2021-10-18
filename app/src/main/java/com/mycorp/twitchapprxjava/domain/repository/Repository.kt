package com.mycorp.twitchapprxjava.domain.repository

import com.mycorp.twitchapprxjava.data.storage.model.GameData
import com.mycorp.twitchapprxjava.data.storage.model.GameDataTable
import com.mycorp.twitchapprxjava.data.storage.model.TwitchResponse
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Observable

interface Repository {
    fun getGamesDataFromNetwork(): Observable<List<GameData>>
    fun getGamesDataFromDb(): Flowable<List<GameData>>
    fun insertGamesDataToDb(gameDataTables:List<GameData>): Completable
}