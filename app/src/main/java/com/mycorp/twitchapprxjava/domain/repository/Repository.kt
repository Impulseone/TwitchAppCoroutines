package com.mycorp.twitchapprxjava.domain.repository

import com.mycorp.twitchapprxjava.data.storage.model.GameDataTable
import com.mycorp.twitchapprxjava.data.storage.model.TwitchResponse
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Observable

interface Repository {
    fun getGamesDataFromNetwork(): Observable<TwitchResponse>
    fun getGamesDataFromDb(): Flowable<List<GameDataTable>>
    fun insertGamesDataToDb(gameDataTables:List<GameDataTable>): Completable
}