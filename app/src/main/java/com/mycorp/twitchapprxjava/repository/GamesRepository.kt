package com.mycorp.twitchapprxjava.repository

import com.mycorp.twitchapprxjava.database.model.GameData
import io.reactivex.Completable
import io.reactivex.Single

interface GamesRepository {
    fun getGamesDataListFromServer(limit: Int, offset: Int): Single<List<GameData>>
    fun getGamesDataFromDb(): Single<List<GameData>>
    fun getGameDataById(id: String): Single<GameData>
    fun insertGamesDataToDb(gameDataList: List<GameData>): Completable
}