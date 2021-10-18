package com.mycorp.twitchapprxjava.data.storage

import com.mycorp.twitchapprxjava.data.storage.model.GameData
import com.mycorp.twitchapprxjava.data.storage.model.GameDataTable
import io.reactivex.Completable
import io.reactivex.Flowable

interface Storage {
   fun getGamesDataFromDb(): Flowable<List<GameDataTable>>
   fun insertGamesData(gamesData: List<GameData>):Completable
}