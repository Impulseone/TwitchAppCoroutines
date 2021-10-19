package com.mycorp.twitchapprxjava.data.storage

import com.mycorp.twitchapprxjava.data.storage.model.GameData
import com.mycorp.twitchapprxjava.data.storage.model.GameDataEntity
import io.reactivex.Completable
import io.reactivex.Single

interface Storage {
    fun getGamesDataFromDb(): Single<List<GameDataEntity>>
    fun insertGamesData(gamesData: List<GameData>): Completable
}