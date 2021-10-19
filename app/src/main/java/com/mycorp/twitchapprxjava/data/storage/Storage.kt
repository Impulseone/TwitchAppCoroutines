package com.mycorp.twitchapprxjava.data.storage

import com.mycorp.twitchapprxjava.data.storage.model.GameData
import com.mycorp.twitchapprxjava.data.storage.model.GameDataTable
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single

interface Storage {
    fun getGamesDataFromDb(): Single<List<GameDataTable>>
    fun insertGamesData(gamesData: List<GameData>): Completable
}